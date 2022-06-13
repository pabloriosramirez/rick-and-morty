package com.pabloriosramirez.api.rickandmorty.aggregator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pabloriosramirez.api.rickandmorty.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.aggregator.AbstractAggregatingMessageGroupProcessor;
import org.springframework.integration.store.MessageGroup;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MainAggregator extends AbstractAggregatingMessageGroupProcessor {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected Object aggregatePayloads(MessageGroup messageGroup, Map<String, Object> map) {
        ObjectNode response = objectMapper.createObjectNode();
        for (Message<?> message : messageGroup.getMessages()) {
            response.set("character", objectMapper.convertValue(message.getPayload(), JsonNode.class));
            response.set("location", objectMapper.convertValue(message.getPayload(), JsonNode.class));
        }
        return response;
    }
}
