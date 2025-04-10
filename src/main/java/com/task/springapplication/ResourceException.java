package com.task.springapplication;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
public class ResourceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final HashMap<String, String> map;

    public ResourceException(HttpStatus httpStatus, HashMap<String, String> map) {
        this.map = map;
        this.httpStatus = httpStatus;
    }

}
