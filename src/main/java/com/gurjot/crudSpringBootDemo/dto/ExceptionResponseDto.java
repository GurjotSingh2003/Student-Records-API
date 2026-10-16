package com.gurjot.crudSpringBootDemo.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private String error;
    private StringBuffer path;

    public ExceptionResponseDto(LocalDateTime timestamp, int statusCode, String message, String error,
            StringBuffer path) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.path = path;
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

}
