package com.example.myproject.security.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    String miClave = "claveprueba123";

    public String generateToken(String nameUser) {
        try {
            return Jwts.builder()
                    .setSubject(nameUser)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiración
                    .signWith(SignatureAlgorithm.HS256, miClave)
                    .compact();
        } catch (Exception e) {
            System.err.println("Error al generar el token JWT: " + e.getMessage());
            throw new RuntimeException("Error al generar el token", e);
        }
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        System.out.println("Extrae el nombre del usuario "+extractedUsername);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(miClave)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer la fecha de expiración del token
    public Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(miClave)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

}
