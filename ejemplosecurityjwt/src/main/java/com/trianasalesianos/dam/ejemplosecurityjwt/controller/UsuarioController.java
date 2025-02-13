package com.trianasalesianos.dam.ejemplosecurityjwt.controller;

import com.trianasalesianos.dam.ejemplosecurityjwt.dto.CreateUserDto;
import com.trianasalesianos.dam.ejemplosecurityjwt.dto.LoginUserDto;
import com.trianasalesianos.dam.ejemplosecurityjwt.dto.UserResponse;
import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import com.trianasalesianos.dam.ejemplosecurityjwt.security.JwtProvider;
import com.trianasalesianos.dam.ejemplosecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal User user) {
        return UserResponse.of(user);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register (@RequestBody CreateUserDto createUserDto){
        User user = usuarioService.createUser(createUserDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.of(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody CreateUserDto createUserDto) {


        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                createUserDto.username(),
                                createUserDto.password()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtProvider.generateToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user));

    }

}
