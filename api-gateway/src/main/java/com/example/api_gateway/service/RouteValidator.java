package com.example.api_gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

// Esta clase valida si la solicitud tiene una ruta válida antes de continuar
@Service
public class RouteValidator {
    public static final List<String> openEndpoints = List.of(
        "/auth"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            openEndpoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

    }

