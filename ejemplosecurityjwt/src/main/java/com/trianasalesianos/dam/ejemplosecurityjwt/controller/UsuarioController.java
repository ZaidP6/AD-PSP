package com.trianasalesianos.dam.ejemplosecurityjwt.controller;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import com.trianasalesianos.dam.ejemplosecurityjwt.model.Usuario;
import com.trianasalesianos.dam.ejemplosecurityjwt.service.UserService;
import com.trianasalesianos.dam.ejemplosecurityjwt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/")
public class UsuarioController {

    private final UserService usuarioService;

    @GetMapping("/{id}")
    public User obtenerUsuario(@RequestParam Long id){
        return usuarioService.getUsuario(id);
    }

    public ResponseEntity<?>
}
