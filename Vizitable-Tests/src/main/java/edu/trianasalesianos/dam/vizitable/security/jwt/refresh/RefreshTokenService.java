package edu.trianasalesianos.dam.vizitable.security.jwt.refresh;

import edu.trianasalesianos.dam.vizitable.security.jwt.access.JwtService;
import edu.trianasalesianos.dam.vizitable.user.dto.user.UsuarioResponse;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;


    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public RefreshToken create(Usuario user) {
        refreshTokenRepository.deleteByUser(user);
        RefreshToken newToken = RefreshToken.builder()
                .user(user)
                .expireAt(Instant.now().plusSeconds(durationInMinutes * 60))
                .build();
        return refreshTokenRepository.save(newToken);
    }


    public RefreshToken verify(RefreshToken refreshToken) {
        if (refreshToken.getExpireAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Token de refresco caducado. Por favor, vuelva a loguearse");
        }

        return refreshToken;

    }

    public UsuarioResponse refreshToken(String token) {
        try {
            UUID refreshTokenUUID = UUID.fromString(token);
            return refreshTokenRepository.findById(refreshTokenUUID)
                    .map(this::verify)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        String accessToken = jwtService.generateAccessToken(user);
                        RefreshToken refreshedToken = this.create(user);
                        return UsuarioResponse.of(user, accessToken, refreshedToken.getToken());
                    })
                    .orElseThrow(() -> new RefreshTokenException("No se ha podido refrescar el token. Por favor, vuelva a loguearse"));
        } catch (IllegalArgumentException e) {
            throw new RefreshTokenException("El formato del token de refresco es inválido.");
        }

    }

}
