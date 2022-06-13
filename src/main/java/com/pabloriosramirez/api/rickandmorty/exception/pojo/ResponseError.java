package com.pabloriosramirez.api.rickandmorty.exception.pojo;


import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ResponseError {
    private int status;
    private String code;
    private String message;
    private String developerMessage;
    private Date timestamp;
    private String traceReferenceId;
    private String reference;

    public ResponseError() {
    }

    public ResponseError(int status, String message) {
        this.status = status;
        this.message = message;
        this.code = HttpStatus.resolve(status).getReasonPhrase();
        this.developerMessage = message;
//        this.reference = reference;
        this.timestamp = new Date();
        this.traceReferenceId = MDC.get("correlationId");
    }

    public ResponseError(int status, String message, String developerMessage, String reference) {
        this.status = status;
        this.code = HttpStatus.resolve(status).getReasonPhrase();
        this.message = message;
        this.developerMessage = developerMessage;
        this.reference = reference;
        this.timestamp = new Date();
        this.traceReferenceId = MDC.get("correlationId");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceReferenceId() {
        return traceReferenceId;
    }

    public void setTraceReferenceId(String traceReferenceId) {
        this.traceReferenceId = traceReferenceId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
