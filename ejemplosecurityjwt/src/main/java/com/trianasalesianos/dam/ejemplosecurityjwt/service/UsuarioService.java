package com.trianasalesianos.dam.ejemplosecurityjwt.service;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.Usuario;
import com.trianasalesianos.dam.ejemplosecurityjwt.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario getUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("usuario no encontrado"));
    }


}
