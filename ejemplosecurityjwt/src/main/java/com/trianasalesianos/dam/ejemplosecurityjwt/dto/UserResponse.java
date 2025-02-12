package com.trianasalesianos.dam.ejemplosecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import java.time.LocalDateTime;
import java.util.UUID;


public record UserResponse (
        UUID id,
        String username,
        String avatar,
        String fullName){



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;


    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static UserResponse of(User user) {
        
    }
}