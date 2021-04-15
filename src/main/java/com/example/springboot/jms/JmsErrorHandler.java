package com.example.springboot.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class JmsErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        LOGGER.error("Failed", t);
    }
}
