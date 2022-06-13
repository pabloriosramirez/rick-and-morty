package com.pabloriosramirez.api.rickandmorty.proxy.exception.domain;

public class InteractionContextException extends RuntimeException {
    public InteractionContextException() {
    }

    public InteractionContextException(String s) {
        super(s);
    }

    public InteractionContextException(Throwable throwable) {
        super(throwable);
    }

    public InteractionContextException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
