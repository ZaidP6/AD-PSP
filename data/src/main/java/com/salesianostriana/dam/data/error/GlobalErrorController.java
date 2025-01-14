package com.salesianostriana.dam.data.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException exception) {

        ProblemDetail detail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        detail.setTitle("No za enkontrao na loko");


        return detail;
    }
}
