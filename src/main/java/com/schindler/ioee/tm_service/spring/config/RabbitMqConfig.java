package com.schindler.ioee.tm_service.spring.config;

import com.schindler.ioee.gdcsv3.common.rabbitmq.RabbitMQConstant;
import com.schindler.ioee.tm_service.util.RabbitConstantData;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litim
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue tmOnlineStatusQueue() {
        return new Queue(RabbitConstantData.Queues.TM_ONLINE_STATUS_QUEUE);
    }

    /**
     * GDCS v3 Exchange
     * @return
     */
    @Bean
    public TopicExchange gdcsv3Exchange() {
        return new TopicExchange(RabbitMQConstant.Exchanges.GDCSV3);
    }

    @Bean
    public Binding tmOnlineStatusQueueBinding(TopicExchange gdcsv3Exchange, Queue tmOnlineStatusQueue) {
        return BindingBuilder.bind(tmOnlineStatusQueue).to(gdcsv3Exchange).with(RabbitMQConstant.RoutingKeys.GDCS_RMP_EQUIPMENT_ONLINESTATUS);
    }
}
