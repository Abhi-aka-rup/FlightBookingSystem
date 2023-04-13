package com.flightbookingsystem.Exceptions;

import java.util.List;

public class ValidationErrorResponse {
    private String message;
    private List<String> errors;

    public ValidationErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
