package edu.trianasalesianos.dam.vizitable.user.dto.user;

public record LoginRequest(
        String username,
        String password,
        int otpCode
) {

}
