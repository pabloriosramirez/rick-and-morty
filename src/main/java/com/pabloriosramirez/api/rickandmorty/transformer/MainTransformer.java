package com.pabloriosramirez.api.rickandmorty.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.MessageProcessor;
import org.springframework.integration.transformer.AbstractMessageProcessingTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MainTransformer {

    @Autowired
    ObjectMapper objectMapper;

    public Message<?> transformPayload(Message<?> message) {
//        Message<Object> currentResponse = MessageBuilder.withPayload(((Map) message.getPayload()).get("CurrentResponse")).build();
        return message;
    }
}
