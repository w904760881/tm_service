package com.schindler.ioee.tm_service.rabbitmq;

import akka.actor.ActorRef;
import com.rabbitmq.client.Channel;
import com.schindler.ioee.gdcsv3.common.model.pojo.EquipmentOnlineStatus;
import com.schindler.ioee.gdcsv3.common.util.JsonUtil;
import com.schindler.ioee.tm_service.factory.ActorFactory;
import com.schindler.ioee.tm_service.util.RabbitConstantData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author litim
 */
@Component
@Slf4j
public class MessageConsumer {
    @Autowired
    private ActorFactory actorFactory;

    @RabbitListener(queues = RabbitConstantData.Queues.TM_ONLINE_STATUS_QUEUE)
    public void processOnlineStatus(String onlineStatusJson, Message message, Channel channel) {
        log.info("received online status:{}", onlineStatusJson);
        try {
            EquipmentOnlineStatus onlineStatus = JsonUtil.readValue(onlineStatusJson, EquipmentOnlineStatus.class);
            actorFactory.getEmailMaster().tell(onlineStatus, ActorRef.noSender());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.info(e.toString());
        }
    }
}
