package com.pabloriosramirez.api.rickandmorty.exception.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConfigurationException extends RuntimeException{

    public ConfigurationException(){
    }
    public ConfigurationException(String s){
        super(s);
    }
    public ConfigurationException(Throwable throwable){
        super(throwable);
    }
    public ConfigurationException(String s, Throwable throwable){
        super(s, throwable);
    }
}
