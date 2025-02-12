package com.trianasalesianos.dam.ejemplosecurityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record CreateUserDto(
        String username,
        String password,
        String verifyPassword,
        String avatar,
        String fullName){

}
