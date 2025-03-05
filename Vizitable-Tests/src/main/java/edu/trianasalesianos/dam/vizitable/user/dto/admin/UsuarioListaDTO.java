package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Role;

import java.util.UUID;

public record UsuarioListaDTO(
        UUID uuid,
        String username,
        String email,
        Role role,
        boolean enabled
) {
    public static UsuarioListaDTO of(Usuario usuario) {
        return new UsuarioListaDTO(
                usuario.getUuid(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.isEnabled()
        );
    }
}