package com.trianasalesianos.dam.ejemplosecurityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public class CreateUserDto{
    
        private String username;
        private String password;
        private String verifyPassword;
        private String avatar;
        private String fullName;

}
