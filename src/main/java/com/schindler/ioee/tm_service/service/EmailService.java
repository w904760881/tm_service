package com.schindler.ioee.tm_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author litim
 */
@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * send email to user
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public boolean send(String from, String to, String subject, String content) {
        log.info("start send email to {}, content is {}", to, content);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            mailSender.send(message);
            log.info("send email to {} successfully!", to);
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}
