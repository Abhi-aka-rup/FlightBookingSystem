package com.flightbookingsystem.Exceptions;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerAspect extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CompletionException.class)
    private ResponseEntity<ErrorResponse> handleCompletionException(CompletionException ex) {
        Throwable cause = ex.getCause();
        switch (cause.getClass().getSimpleName()) {
            case "NoSuchElementException":
                return handleNoSuchElementException((NoSuchElementException) cause);
            case "EntityNotFoundException":
                return handleEntityNotFoundException((EntityNotFoundException) cause);
            default:
                return handleRuntimeException((RuntimeException) cause);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }
}
