package com.schindler.ioee.tm_service.spring.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.github.pagehelper.PageInterceptor;
import com.schindler.ioee.tm_service.factory.ActorFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.schindler.ioee.tm_service.spring.actor.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * @author litim
 */
@Configuration
@MapperScan("com.schindler.ioee.tm_service.mybatis.mapper")
public class SpringConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("tm-service-system");
        SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);

        return system;
    }

    @Bean
    public ActorFactory actorFactory() {
        ActorRef emailMaster = actorSystem().actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem()).props("emailMaster"), "emailMaster");
        ActorRef messageDeliveryActor = actorSystem().actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem()).props("messageDeliveryActor"), "messageDeliveryActor");

        return new ActorFactory(emailMaster, messageDeliveryActor);
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
