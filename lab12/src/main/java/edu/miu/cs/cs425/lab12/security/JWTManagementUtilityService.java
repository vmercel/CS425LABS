package edu.miu.cs.cs425.lab12.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JWTManagementUtilityService {
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(
            "this-is-a-very-long-secret-key-for-jwt-that-is-at-least-64-bytes-long!!".getBytes()
    );
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            System.err.println("Error extracting username from token: " + e.getMessage());
            throw e;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}