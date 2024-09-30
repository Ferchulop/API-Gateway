package com.example.authservice.services;

import com.example.authservice.commons.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

// La interfaz JwtService define un contrato para los servicios relacionados con JWT, genera token, obtiene reclamación, comprueba expiración y recupera Id usuario.
public interface JwtService {
    TokenResponse generateToken(Long userId);

    Claims getClaims(String token);

    boolean isExpired(String token);

    Integer extractUserId(String token);
}
