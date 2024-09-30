package com.example.authservice.services.impl;

import com.example.authservice.commons.dtos.TokenResponse;
import com.example.authservice.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Date;


@Service
public class JwtServiceImpl implements JwtService {
    private final String secretKey;

    // Constructor que recibe la clave secreta desde la configuración de la aplicación.
    public JwtServiceImpl(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    // Se genera un token JWT para el usuario dado.
    @Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);// Establece la fecha de expiración del token.
        String token = Jwts.builder()  // Comienza la construcción del token.
                .setSubject(String.valueOf(userId)) // Establece el ID del usuario como el sujeto del token.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Establece la fecha de emisión del token.
                .setExpiration(expirationDate) // Establece la fecha de expiración del token.
                .signWith(SignatureAlgorithm.HS512, this.secretKey) // Firma el token con el algoritmo HS512 y la clave secreta.
                .compact();  // Compone el token en una cadena.
        return TokenResponse.builder()
                .accesstoken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secretKey) // Establece la clave secreta para verificar la firma.
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    // Verifica si el token ha expirado.
    @Override
    public boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date()); // Comprueba si la fecha de expiración es anterior a la fecha actual.
        } catch (Exception e) {
            return false;
        }
    }

    // Extrae el ID del usuario del token
    @Override
    public Integer extractUserId(String token) {
        try {
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
