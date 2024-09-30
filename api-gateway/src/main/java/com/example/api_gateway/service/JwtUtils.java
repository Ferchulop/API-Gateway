package com.example.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
// Esta clase maneja la generación, validación y extracción de información de los tokens JWT.
@Service
public class JwtUtils {
    // Clave secreta utilizada para firmar y verificar los tokens JWT.
    private final String secretKey = "fasdfasdfsdfsdsdffdsfdssdfaddafdsffdsasdfdfsadsfadfdsfdsadfsfdasfdasdfsasfsasdfsdasfafasdsadasfddfasdfa";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isExpired(String token) {
        // Manejo de errores en caso de que el token sea inválido.
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
    //Método que extrae el ID del usuario desde el token JWT
    public Integer extractUserId(String token) {
        try{
            return  Integer.parseInt(getClaims(token).getSubject());
        }catch (Exception e){
            return null;
        }
    }

}
