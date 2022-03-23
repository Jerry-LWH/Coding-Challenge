package com.example.bestmatchedrestarant.controller;

import java.util.ArrayList;
import javax.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handle(ConstraintViolationException exception) {
        var violations = new ArrayList<>(exception.getConstraintViolations());
        String errorMessage = violations.get(0).getPropertyPath() + " " + violations.get(0).getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
