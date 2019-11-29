package com.schindler.ioee.tm_service.factory;

import akka.actor.ActorRef;

/**
 * @author litim
 */
public class ActorFactory {

    private ActorRef emailMaster;

    private ActorRef messageDeliveryActor;

    public ActorRef getEmailMaster() {
        return emailMaster;
    }

    public ActorRef getMessageDeliveryActor() {
        return messageDeliveryActor;
    }

    public ActorFactory(ActorRef emailMaster,ActorRef messageDeliveryActor){
        this.emailMaster=emailMaster;
        this.messageDeliveryActor=messageDeliveryActor;
    }
}
