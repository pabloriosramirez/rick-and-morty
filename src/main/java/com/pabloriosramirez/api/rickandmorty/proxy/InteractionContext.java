package com.pabloriosramirez.api.rickandmorty.proxy;

import com.pabloriosramirez.api.rickandmorty.proxy.exception.domain.InteractionContextException;

import java.util.Set;

public interface InteractionContext {

    public <T> T getProperty(String key, Class<T> type) throws InteractionContextException;

    public Set<String> getPropertyKeys() throws InteractionContextException;

    public void setProperty(String key, Object value) throws InteractionContextException;

}
