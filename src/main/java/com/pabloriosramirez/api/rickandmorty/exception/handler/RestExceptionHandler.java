package com.pabloriosramirez.api.rickandmorty.exception.handler;

import com.pabloriosramirez.api.rickandmorty.activator.impl.BasicRestServiceActivator;
import com.pabloriosramirez.api.rickandmorty.exception.pojo.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(BasicRestServiceActivator.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(e, e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception e, WebRequest request){
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(e, e.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnsupportedOperationException(Exception e, WebRequest request){
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(e, e.getMessage(), headers, HttpStatus.NOT_IMPLEMENTED, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("Handling exception at global level.", exception);
        ResponseError response;
        Throwable rootCause = getRootCause(exception);
        if ((rootCause != null) && (rootCause != exception)) {
            String developerMessage = exception.getMessage();
            developerMessage = placePoint(developerMessage)
                    + developerMessage
                    + " Caused by: "
                    + ((rootCause.getMessage() != null) ? rootCause.getMessage() : rootCause)
                    + placePoint(developerMessage)
                    + placeLastCall(rootCause, developerMessage);
            response = new ResponseError(status.value(), exception.getMessage(), developerMessage, null);
        } else {
            response = new ResponseError(status.value(), exception.getMessage());
        }
        LOGGER.error("Returning error response {}", response);

        return new ResponseEntity<>(response, headers, status);
    }

    private String placeLastCall(Throwable rootCause, String developerMessage) {
        if ((rootCause.getStackTrace() != null) && (rootCause.getStackTrace().length > 0)) {
            developerMessage = developerMessage + " Last call: " + rootCause.getStackTrace()[0];
        }
        return developerMessage;
    }

    private String placePoint(String developerMessage) {
        if (!developerMessage.endsWith(".")) {
            developerMessage = developerMessage + ".";
        }
        return developerMessage;
    }

    protected Throwable getRootCause(Exception exception) {
        if (exception == null) {
            return null;
        }
        Throwable cause = exception;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }
}
