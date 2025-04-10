package com.task.springapplication.controllers;

import com.task.springapplication.ResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<HashMap<String, String>> handleException(ResourceException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMap());
    }
}
