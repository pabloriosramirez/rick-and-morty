package com.pabloriosramirez.api.rickandmorty.transformer.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class BasicTransformer {
    private final static Logger LOGGER = LoggerFactory.getLogger(BasicTransformer.class);

    @Autowired
    ObjectMapper objectMapper;

    public Message transformPayload(Message message) {
        return message;
    }
}
