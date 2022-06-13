package com.pabloriosramirez.api.rickandmorty.activator;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public interface ServiceActivator {
    Map<String, Object> invoke(@Payload Map<String, Object> payload,@Headers Map<String, Object> headers) throws Exception;
}
