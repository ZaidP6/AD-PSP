package com.trianasalesianos.dam.ejemplosecurityjwt.controller;

import com.trianasalesianos.dam.ejemplosecurityjwt.dto.CreateUserDto;
import com.trianasalesianos.dam.ejemplosecurityjwt.dto.LoginUserDto;
import com.trianasalesianos.dam.ejemplosecurityjwt.dto.UserResponse;
import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import com.trianasalesianos.dam.ejemplosecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService usuarioService;

    @GetMapping("/{id}")
    public User obtenerUsuario(@RequestParam UUID id){
        return usuarioService.findById(id);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register (@RequestBody CreateUserDto createUserDto){
        User user = usuarioService.createUser(createUserDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.of(user));
    }

    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto){
        Authentication authentication = AuthenticationManager
    }

}
