package com.switchwon.payment.error.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class SwitchwonException extends RuntimeException {

    private  String errorMessage;

    public SwitchwonException(String message) {
        super(message);
    }
}
