package com.Coworking.demo.security;

import com.Coworking.demo.modules.usuario.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {


    @Value("${Coworking.token.secret}")
    private String secret;


    public String generateToken(Usuario usuario) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("Coworking")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }


    public String validateToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);


            String email = JWT.require(algorithm)
                    .withIssuer("Coworking")
                    .build()
                    .verify(token)
                    .getSubject();

            return email;
        } catch (JWTVerificationException exception) {

            System.out.println("Erro ao validar o token: " + exception.getMessage());
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
