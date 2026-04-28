package com.customer.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ApiError(int status, String message) {
        this(status, message, null);
    }

    public ApiError(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
