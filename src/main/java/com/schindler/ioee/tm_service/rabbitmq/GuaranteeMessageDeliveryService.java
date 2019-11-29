package com.schindler.ioee.tm_service.rabbitmq;

import akka.actor.ActorRef;
import com.schindler.ioee.gdcsv3.common.rabbitmq.CorrelationIdGenerator;
import com.schindler.ioee.gdcsv3.common.rabbitmq.MessageDeliveryParameter;
import com.schindler.ioee.gdcsv3.common.rabbitmq.MessageDeliveryStatus;
import com.schindler.ioee.gdcsv3.common.rabbitmq.model.MessageDeliveryLog;
import com.schindler.ioee.tm_service.factory.ActorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.SECONDS;


/**
 * @author litim
 */
@Service
@Slf4j
public class GuaranteeMessageDeliveryService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ActorFactory actorFactory;

    @Autowired
    public GuaranteeMessageDeliveryService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        setupCallbacks();
    }

    private void setupCallbacks() {
        this.rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
            if (correlation != null) {
                log.info("correction:{}, ack:{}, reason:{}", correlation, ack, reason);
                MessageDeliveryLog deliveryLog = new MessageDeliveryLog();
                deliveryLog.setCorrelationId(correlation.getId());
                deliveryLog.setDeliveryStatus(MessageDeliveryStatus.SUCCESS.getValue());
                actorFactory.getMessageDeliveryActor().tell(deliveryLog, ActorRef.noSender());
            }
        });

        this.rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                    log.info("message:{}, replyCode:{}, replyText:{}, exchange:{}, routingKey:{}", message, replyCode, replyText, exchange, routingKey);
                    String correlationId = message.getMessageProperties().getCorrelationId();
                    MessageDeliveryLog deliveryLog = new MessageDeliveryLog();
                    deliveryLog.setCorrelationId(correlationId);
                    deliveryLog.setDeliveryStatus(MessageDeliveryStatus.NO_ROUTER.getValue());
                }
        );
    }


    public boolean deliver(String exchange, String routingKey, String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date nextDeliveryTime = Date.from(localDateTime.plus(MessageDeliveryParameter.NEXT_DELIVERY_TIME_INTERVAL, SECONDS).atZone(ZoneId.systemDefault()).toInstant());
        String correlationId = CorrelationIdGenerator.generate(currentDate);
        MessageDeliveryLog deliveryLog = new MessageDeliveryLog();
        deliveryLog.setCorrelationId(correlationId);
        deliveryLog.setExchange(exchange);
        deliveryLog.setRoutingKey(routingKey);
        deliveryLog.setMessage(message);
        deliveryLog.setNextDeliveryTime(nextDeliveryTime);
        deliveryLog.setDeliveryStatus(MessageDeliveryStatus.INIT.getValue());
        deliveryLog.setDeliveryCount(1);
        deliveryLog.setCreateDate(currentDate);
        deliveryLog.setUpdateDate(currentDate);
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData(correlationId));
        log.info("deliver message: exchange={}, routingKey={}, message={}", exchange, routingKey, message);
        actorFactory.getMessageDeliveryActor().tell(deliveryLog, ActorRef.noSender());
        return true;
    }
}
