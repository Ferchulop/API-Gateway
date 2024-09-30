package com.example.api_gateway.service;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Filtro de autenticación que verifica el token JWT antes de permitir la solicitud
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;

    private final JwtUtils jwtUtils;

    public AuthenticationFilter(RouteValidator validator, JwtUtils jwtUtils) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtils = jwtUtils;
    }
    // Método que aplica el filtro de autenticación a las solicitudes.
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            var request = exchange.getRequest();

            ServerHttpRequest serverHttpRequest = null;
            if (validator.isSecured.test(request)) {
                if (authMissing(request)) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                // Extrae el token JWT del encabezado Authorization.
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer")) {
                    authHeader = authHeader.substring(7);
                } else {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                if (jwtUtils.isExpired(authHeader)) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                serverHttpRequest = exchange.getRequest().mutate().header("userIdRequest", jwtUtils.extractUserId(authHeader).toString()).build();
            }
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        });
    }
    // Método que devuelve un error cuando hay problemas con la autenticación.
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return null;
    }
    // Verifica si falta el encabezado de autorización en la solicitud.
    private boolean authMissing(ServerHttpRequest request) {

        return !request.getHeaders().containsKey("Authorization");
    }
    public static class Config {}

}
