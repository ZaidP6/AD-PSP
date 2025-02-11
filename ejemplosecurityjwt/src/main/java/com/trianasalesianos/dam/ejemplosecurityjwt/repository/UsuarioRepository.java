package com.trianasalesianos.dam.ejemplosecurityjwt.repository;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByUsername(String username);
}
