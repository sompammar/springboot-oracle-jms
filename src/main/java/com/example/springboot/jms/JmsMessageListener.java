package com.example.springboot.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Enumeration;

@Component
public class JmsMessageListener implements SessionAwareMessageListener<Message> {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    /**
     * Callback for processing a received JMS message.
     * <p>Implementors are supposed to process the given Message,
     * typically sending reply messages through the given Session.
     *
     * @param message the received JMS message (never {@code null})
     * @param session the underlying JMS Session (never {@code null})
     * @throws JMSException if thrown by JMS methods
     */

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        try {
            logger.info("JMS Message received : {} " , message);
            Enumeration props= message.getPropertyNames();
            while (props.hasMoreElements()) {
                String property = (String) props.nextElement();
                logger.info("Property : {}  Value: {}", property, message.getStringProperty(property));

            }
            session.commit();
        } catch (Exception e) {
            logger.error("Error while processing the Received Message - ", e);
            session.rollback();
        }
    }
}
