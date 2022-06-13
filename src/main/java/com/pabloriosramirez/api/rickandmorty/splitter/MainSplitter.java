package com.pabloriosramirez.api.rickandmorty.splitter;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MainSplitter extends AbstractMessageSplitter {

    @Override
    protected List<Message<Integer>> splitMessage(Message<?> message) {
        List messages = new ArrayList();
        messages.add(message);
        return messages;
    }
}
