package com.example.springboot.jms;

import com.example.springboot.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class JmsMessageSender {

    final static String CHARSET = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageSender.class);

    @Autowired
    @Qualifier("jobRequestJmsTemplate")
    private JmsTemplate jobRequestJmsTemplate;

    public void sendMessage(final String message, final String jmsRequestId, final JmsActionType jmsActionType) {
        logger.info("Preparing to send JMS message - {} ", message);
        try {

                jobRequestJmsTemplate.send(session -> {
                    String correlationID = UUID.randomUUID().toString();
                    BytesMessage bytesMessage = session.createBytesMessage();
                    bytesMessage.setStringProperty(Constants.X_JMS_REQUEST_ID, jmsRequestId);
                    bytesMessage.setStringProperty(Constants.JMS_ACTION_TYPE, jmsActionType.name());
                    bytesMessage.setStringProperty("message", message);
                    bytesMessage.setJMSCorrelationID(correlationID);
                    return bytesMessage;
                });
                logger.info("Message sent");
            //}
        } catch (Exception e) {
            logger.error("Error while sending message to JMS Queue - ", e);
            throw new RuntimeException("Error while sending message to JMS Queue", e);
        }
    }

}
