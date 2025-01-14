package com.example.demo.error;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id){
        super("No hay producto con ese ID:")
    }
}
