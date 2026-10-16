package com.gurjot.crudSpringBootDemo.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidExceptionResponseDto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private String error;
    private StringBuffer path;
    private Map<String, String> fieldErrors;

    public ValidExceptionResponseDto(LocalDateTime timestamp, int statusCode, String message, String error,
            StringBuffer path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public StringBuffer getPath() {
        return path;
    }

    public void setPath(StringBuffer path) {
        this.path = path;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}
