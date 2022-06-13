package com.pabloriosramirez.api.rickandmorty.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pabloriosramirez.api.rickandmorty.gateway.MainGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/api"})
public class MainController {

    final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    MainGateway gateway;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public JsonNode main(@PathVariable("id") Integer id){
        LOGGER.info("[CONTROLLER] Processing main.");
        ObjectNode uriVariables = objectMapper.createObjectNode();
        uriVariables.put("id", id);
        Map<String, Object> payload = new HashMap<>();
        payload.put("CurrentResponse", uriVariables);
        JsonNode response = gateway.process(MessageBuilder.withPayload(payload).build());
        LOGGER.info("[CONTROLLER] Ending main.");
        return response;
    }
}
