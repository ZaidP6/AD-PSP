package edu.trianasalesianos.dam.vizitable.user.dto.user;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;

import java.util.UUID;

public record UsuarioSimpleDTO(
        UUID id,
        String username
) {
    public static UsuarioSimpleDTO of(Usuario usuario) {
        return new UsuarioSimpleDTO(usuario.getUuid(), usuario.getUsername());
    }
}
