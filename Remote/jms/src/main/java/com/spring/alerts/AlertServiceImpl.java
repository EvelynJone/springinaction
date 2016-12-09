package com.spring.alerts;

import com.spring.domain.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Class Name : AlertServiceImpl<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/917:08<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
//@Component("alertService")
public class AlertServiceImpl implements AlertService {

    private JavaMailSender mailSender;
    private String alertEmailAddress;

    public AlertServiceImpl(JavaMailSender mailSender, String alertEmailAddress) {
        this.mailSender = mailSender;
        this.alertEmailAddress = alertEmailAddress;
    }

    private JmsOperations jmsOperations;

    @Autowired
    public AlertServiceImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    public void sendSpittleAlert(final Spittle spittle) {
        jmsOperations.send("spittle.alert.queue",   // 指定目的地
                new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        return session.createObjectMessage(spittle); // 创建消息
                    }
                });

        //在发送时，对消息进行转换
        jmsOperations.convertAndSend(spittle);

 /*       SimpleMailMessage message = new SimpleMailMessage();
        String spitterName = spittle.getSpitter().getFullName();
        message.setFrom("noreply@spitter.com");
        message.setTo(alertEmailAddress);
        message.setSubject("New spittle from " + spitterName);
        message.setText(spitterName + " says: " + spittle.getMessage());
        mailSender.send(message);*/

    }

    public Spittle receiveSpittleAlert() {
        try {
            ObjectMessage receiveMessage = (ObjectMessage) jmsOperations.receive();
            return (Spittle) receiveMessage.getObject();
        } catch (JMSException jmsException) {
            // 跑出转换后的异常
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
        // 接受并转换
//        return (Spittle) jmsOperations.receiveAndConvert();
    }
}
