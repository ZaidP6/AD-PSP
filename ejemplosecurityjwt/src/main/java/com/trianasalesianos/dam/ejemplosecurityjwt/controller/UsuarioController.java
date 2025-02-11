package com.trianasalesianos.dam.ejemplosecurityjwt.controller;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.Usuario;
import com.trianasalesianos.dam.ejemplosecurityjwt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@RequestParam Long id){
        return usuarioService.getUsuario(id);
    }
}
