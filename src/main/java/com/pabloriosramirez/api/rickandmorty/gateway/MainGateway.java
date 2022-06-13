package com.pabloriosramirez.api.rickandmorty.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;

import java.util.Map;

public interface MainGateway {
    @Gateway
    JsonNode process(Message<Map<String, Object>> payload);
}
