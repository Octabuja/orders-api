package com.octavio.orders_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, Object> handleResponseStatusException(ResponseStatusException ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("message", ex.getReason());
        error.put("status", ex.getStatusCode().value());

        return error;
    }
}
