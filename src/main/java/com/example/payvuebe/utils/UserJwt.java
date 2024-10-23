package com.example.payvuebe.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class UserJwt {
    // Secret key to sign the token
    private static final String SECRET_KEY = "Heeneth";

    // Expiration time for the token (in milliseconds)
    private static final long EXPIRATION_TIME = 86400000;

    // Method to create JWT token
    public String createToken(Long id) {
        return JWT.create()
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8)));
    }

    // Method to decode JWT token
    public Long decodeToken(String token) {
        try {
            Long userId = JWT.require(Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .verify(token)
                    .getClaim("id")
                    .asLong();
            System.out.println("Decoded userId: " + userId);
            return userId;
        } catch (Exception e) {
            // Handle token decoding exception
            e.printStackTrace();
            System.err.println("Token decoding failed: " + e.getMessage());
            return null;
        }
    }
}