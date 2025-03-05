package edu.trianasalesianos.dam.vizitable.user.error;

import edu.trianasalesianos.dam.vizitable.user.error.dto.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Hidden
@RestControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ApiErrorResponse> handleMessageException(MessageException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                ex.getErrorType().getMessage(),
                ex.getErrorType().getStatus(),
                ex.getErrorType().getStatus().value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(ex.getErrorType().getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
