package com.sltc.depositos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("message", ex.getMessage());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("message", "Error interno: " + ex.getMessage());
        error.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
