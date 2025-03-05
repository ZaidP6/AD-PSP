package edu.trianasalesianos.dam.vizitable.user.error.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String message,
        HttpStatus status,
        int statusCode,
        LocalDateTime timestamp
) {


}
