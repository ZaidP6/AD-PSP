package edu.trianasalesianos.dam.vizitable.user.dto.user;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Role;

import java.util.UUID;

public record PerfilResponseDTO(
        UUID uuid,
        String username,
        String fullname,
        String email
) {
    public static PerfilResponseDTO of(Usuario usuario) {
        return new PerfilResponseDTO(
                usuario.getUuid(),
                usuario.getUsername(),
                usuario.getFullname(),
                usuario.getEmail()
        );
    }
}
