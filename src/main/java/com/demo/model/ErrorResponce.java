package com.demo.model;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponce {
    public int httpCode;
    public String message;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
