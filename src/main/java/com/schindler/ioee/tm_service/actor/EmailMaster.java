package com.schindler.ioee.tm_service.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.schindler.ioee.tm_service.spring.actor.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * @author litim
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmailMaster extends AbstractActor {

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("start EmailMaster {}.", getSelf());
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("stop EmailMaster {}.", getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(message -> handleMessage(message))
                .build();
    }

    private void handleMessage(Object message) {
        getContext().actorOf(SPRING_EXTENSION_PROVIDER.get(getContext().system()).props("emailActor"))
                .tell(message, getSelf());
    }
}
