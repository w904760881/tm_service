package com.schindler.ioee.tm_service.actor;

import akka.actor.AbstractActorWithTimers;
import com.schindler.ioee.gdcsv3.common.model.actor.HeartbeatTimeTick;
import com.schindler.ioee.gdcsv3.common.rabbitmq.MessageDeliveryParameter;
import com.schindler.ioee.gdcsv3.common.rabbitmq.MessageDeliveryStatus;
import com.schindler.ioee.gdcsv3.common.rabbitmq.model.MessageDeliveryLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.concurrent.duration.Duration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author litim
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MessageDeliveryActor extends AbstractActorWithTimers {

    private final static String MESSAGE_DELIVERY_TIME_TICK = "MessageDeliveryTimeTick";

    private final int MESSAGE_DELIVERY_INTERVAL = 20;

    /**
     * CorrelationId and MessageDeliverLog map
     */
    private Map<String, MessageDeliveryLog> cachedMessageDeliveryLog = new HashMap<>();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void preStart() throws Exception {
        log.info("Start MessageDeliveryActor {}.", getSelf());
        scheduleDeliveryMessageTask();
        super.preStart();
    }


    @Override
    public void postStop() throws Exception {
        log.info("Stop MessageDeliveryActor {}.", getSelf());
        super.postStop();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(HeartbeatTimeTick.class, TimeTick -> deliveryMessage())
                .match(MessageDeliveryLog.class, messageDeliveryLog -> handleMessageDeliveryLog(messageDeliveryLog))
                .build();
    }

    private void deliveryMessage() {
        log.info("start to deliver expired message...");
        Date currentTime = new Date();
        for (MessageDeliveryLog deliveryLog : cachedMessageDeliveryLog.values()) {
            if (deliveryLog.getDeliveryCount() >= MessageDeliveryParameter.MAX_DELIVERY_COUNT) {
                log.info("remove cached message {}, time expired.", deliveryLog);
                cachedMessageDeliveryLog.remove(deliveryLog.getCorrelationId());
                continue;
            }

            if (deliveryLog.getNextDeliveryTime().before(currentTime)) {
                log.info("re-deliver message {}", deliveryLog);
                rabbitTemplate.convertAndSend(deliveryLog.getExchange(), deliveryLog.getRoutingKey(), deliveryLog.getMessage());
                deliveryLog.setDeliveryCount(deliveryLog.getDeliveryCount() + 1);
                Date nextDeliveryTime = Date.from(LocalDateTime.now().plus(MessageDeliveryParameter.NEXT_DELIVERY_TIME_INTERVAL, SECONDS).atZone(ZoneId.systemDefault()).toInstant());
                deliveryLog.setNextDeliveryTime(nextDeliveryTime);
                deliveryLog.setUpdateDate(currentTime);
            }
        }
    }

    private void handleMessageDeliveryLog(MessageDeliveryLog messageDeliveryLog) {
        if (messageDeliveryLog.getDeliveryStatus().equals(MessageDeliveryStatus.INIT.getValue())) {
            log.info("add message delivery log {}", messageDeliveryLog);
            cachedMessageDeliveryLog.put(messageDeliveryLog.getCorrelationId(), messageDeliveryLog);
        } else {
            log.info("remove message delivery log {}", messageDeliveryLog);
            cachedMessageDeliveryLog.remove(messageDeliveryLog.getCorrelationId());
        }
    }

    private void scheduleDeliveryMessageTask() {
        log.info("upload Callback Scheduler Task!");
        getTimers().startPeriodicTimer(MESSAGE_DELIVERY_TIME_TICK, new HeartbeatTimeTick(System.currentTimeMillis()),
                Duration.create(MESSAGE_DELIVERY_INTERVAL, TimeUnit.SECONDS));
    }
}
