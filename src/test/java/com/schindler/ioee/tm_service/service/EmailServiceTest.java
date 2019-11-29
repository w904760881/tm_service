package com.schindler.ioee.tm_service.service;

import com.schindler.ioee.tm_service.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String from;

    @Test
    public void send() {
        String content = "Dear Sir:<br> Please check the device online notification. Don't reply this email! <br>" +
                "SAP 号: 42012043 <br>" +
                "合同号: E1630244 <br>" +
                "盒子编号: 2102351HNADMJ9000828 <br>" +
                "TM 首次上线时间: 2019-11-21 13:51:45 <br>";
        emailService.send(from,"tim.li@schindler.com","TM Device On-line Notification",content);
    }
}