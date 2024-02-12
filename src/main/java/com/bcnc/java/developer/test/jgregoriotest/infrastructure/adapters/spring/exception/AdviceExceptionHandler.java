package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleResourceNotFoundException(IllegalArgumentException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);// TODO JG improve json format response
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGeneralException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("Internal error");// TODO JG improve json format response
    }
}