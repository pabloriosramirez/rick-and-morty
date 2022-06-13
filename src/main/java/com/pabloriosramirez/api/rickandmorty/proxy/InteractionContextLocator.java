package com.pabloriosramirez.api.rickandmorty.proxy;

import com.pabloriosramirez.api.rickandmorty.proxy.exception.domain.InteractionContextException;

public interface InteractionContextLocator {
    public InteractionContext  getInteractionContext(boolean create) throws InteractionContextException;
    public InteractionContext getInteractionContextCopy() throws InteractionContextException;
    public void setInteractionContext(InteractionContext interactionContext) throws InteractionContextException;
    public void clearInteractionContext() throws InteractionContextException;
}
