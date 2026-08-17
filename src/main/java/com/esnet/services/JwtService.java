package com.esnet.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
            "medrassa_ecole_coranique_beni_haoua_2026_secret_key";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );

    // ==========================================
    // GENERER LE TOKEN
    // ==========================================

    public String generateToken(
            String username,
            String role) {

        return Jwts.builder()

                .subject(username)

                .claim("role", role)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 24
                        )
                )

                .signWith(key)

                .compact();
    }

    // ==========================================
    // EXTRAIRE LES CLAIMS
    // ==========================================

    private Claims extractClaims(String token) {

        return Jwts.parser()

                .verifyWith(key)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    // ==========================================
    // EXTRAIRE EMAIL
    // ==========================================

    public String extractUsername(String token) {

        return extractClaims(token)
                .getSubject();
    }

    // ==========================================
    // EXTRAIRE ROLE
    // ==========================================

    public String extractRole(String token) {

        return extractClaims(token)
                .get("role", String.class);
    }

    // ==========================================
    // EXPIRATION
    // ==========================================

    public Date extractExpiration(String token) {

        return extractClaims(token)
                .getExpiration();
    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    // ==========================================
    // VALIDATION
    // ==========================================

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        return extractUsername(token)
                .equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }
}