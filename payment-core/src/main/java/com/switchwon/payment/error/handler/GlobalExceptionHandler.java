package com.switchwon.payment.error.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.switchwon.payment.error.ErrorResponse;
import com.switchwon.payment.error.exception.SwitchwonException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SwitchwonException.class)
    public ResponseEntity<Object> handleSwitchwonException(WebRequest request, SwitchwonException e) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(WebRequest request, SwitchwonException e) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
