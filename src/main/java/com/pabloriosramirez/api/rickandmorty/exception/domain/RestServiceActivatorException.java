package com.pabloriosramirez.api.rickandmorty.exception.domain;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RestServiceActivatorException extends RuntimeException {

    public RestServiceActivatorException() {
    }

    public RestServiceActivatorException(String s) {
        super(s);
    }

    public RestServiceActivatorException(Throwable throwable) {
        super(throwable);
    }

    public RestServiceActivatorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RestServiceActivatorException(String s, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(s, throwable, enableSuppression, writableStackTrace);
    }
}
