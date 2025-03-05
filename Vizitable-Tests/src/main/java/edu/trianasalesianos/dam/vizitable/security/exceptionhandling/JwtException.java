package edu.trianasalesianos.dam.vizitable.security.exceptionhandling;

public class JwtException extends RuntimeException{
    public JwtException(String message) {
        super(message);
    }
}
