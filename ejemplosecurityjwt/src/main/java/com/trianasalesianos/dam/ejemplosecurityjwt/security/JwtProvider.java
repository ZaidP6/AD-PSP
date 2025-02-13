package com.trianasalesianos.dam.ejemplosecurityjwt.security;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
//import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

//@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    //private int jwtLifeInDays;
    @Value("${jwt.duration}")
    private int jwtLifeInMinutes;

    private JwtParser jwtParser;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {

        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

//        jwtParser = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build();
    }


    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return generateToken(user);

    }

    public String generateToken(User user) {
        Date tokenExpirationDateTime =
                Date.from(
                        LocalDateTime
                                .now()
                                //.plusDays(jwtLifeInDays)
                                .plusMinutes(jwtLifeInMinutes)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                );

        return Jwts.builder()
                .header().type(TOKEN_TYPE)
                .and()
                .Subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(tokenExpirationDateTime)
                .signWith(secretKey)
                .compact();

    }

    public UUID getUserIdFromJwtToken(String token) {
        String sub = jwtParser.parseClaimsJws(token).getBody().getSubject()
        return UUID.fromString(sub);
    }


    public boolean validateToken(String token) {

        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch(SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Error con el token: " + ex.getMessage());
            throw new JwtException(ex.getMessage());
        }
        //return false;

    }
}
