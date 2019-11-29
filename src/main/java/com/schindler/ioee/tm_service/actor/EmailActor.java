package com.schindler.ioee.tm_service.actor;

import akka.actor.AbstractActor;
import com.schindler.ioee.gdcsv3.common.model.pojo.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.service.EmailService;
import com.schindler.ioee.tm_service.service.EquipmentService;
import com.schindler.ioee.tm_service.util.Constant;
import com.schindler.ioee.tm_service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author litim
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmailActor extends AbstractActor {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EquipmentService equipmentService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("start EmailActor {}.", getSelf());
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("stop EmailActor {}.", getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(message -> handleMessage(message))
                .build();
    }

    private void handleMessage(Object message) {
        log.info("receive message: {}", message);
        if (message instanceof EquipmentOnlineStatus) {
            handleOnlineStatus((EquipmentOnlineStatus) message);
        } else {
            unhandled(message);
        }
        getContext().stop(getSelf());
    }

    /***
     * handle Online Status
     * @param onlineStatus
     */
    private void handleOnlineStatus(EquipmentOnlineStatus onlineStatus) {
        log.debug("emailActor receive OnlineStatus: {}", onlineStatus);
        Optional<com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus> equipmentTm = equipmentService.getEquipmentById(onlineStatus.getEquipmentID());
        if (!equipmentTm.isPresent()) {
            return;
        }
        boolean result = true;
        if (equipmentTm.get().getNotifyTime() == null) {
            String content = "Dear Sir:<br> Please check the device online notification.Don't reply this email! <br>" +
                    "SAP 号: " + equipmentTm.get().getEquipmentId() + "<br>" +
                    "合同号: " + equipmentTm.get().getContractNo() + "<br>" +
                    "盒子编号: " + equipmentTm.get().getCubeNo() + "<br>" +
                    "TM 首次上线时间: " + DateUtil.yyyy_MM_ddHH_mm_ss_Format(onlineStatus.getLastCommunicationTime()).get() + "";
            result = emailService.send(from, equipmentTm.get().getEmail(), Constant.EMAIL_SUBJECT, content);
            equipmentTm.get().setNotifyTime(onlineStatus.getLastCommunicationTime());
        }
        if (result) {
            equipmentTm.get().setHeartbeatTime(onlineStatus.getLastCommunicationTime());
            equipmentService.update(equipmentTm.get());
        }
    }
}
