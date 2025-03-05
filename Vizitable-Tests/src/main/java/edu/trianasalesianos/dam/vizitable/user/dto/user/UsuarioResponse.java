package edu.trianasalesianos.dam.vizitable.user.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;

import java.util.UUID;

public record UsuarioResponse (

    UUID id,
    String username,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String token,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String refreshToken,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String secretKey

) {

    public static UsuarioResponse of(Usuario user) {
        return new UsuarioResponse(user.getUuid(), user.getUsername(), null, null, null);
    }

    public static UsuarioResponse of(Usuario usuario, String token, String refreshToken) {
        return new UsuarioResponse(usuario.getUuid(), usuario.getUsername(), token, refreshToken, null);
    }

    public static UsuarioResponse withSecret(Usuario usuario, String secretKey) {
        return new UsuarioResponse(usuario.getUuid(), usuario.getUsername(), null, null, secretKey);
    }
}
