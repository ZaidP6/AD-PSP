package edu.trianasalesianos.dam.vizitable.security.jwt.access;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private long jwtLifeInMinutes;

    private JwtParser jwtParser;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {

        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    public String generateAccessToken(Usuario user) {
        Date tokenExpirationDate =
                Date.from(
                        LocalDateTime
                                .now()
                                .plusMinutes(jwtLifeInMinutes)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                );

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(user.getUuid().toString())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public UUID getUserIdFromAccessToken(String token) {
        String sub = jwtParser.parseClaimsJws(token).getBody().getSubject();
        return UUID.fromString(sub);
    }

    public boolean validateAccessToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch(SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new JwtException(ex.getMessage());
        }
    }
}
