package com.example.authservice.services.impl;

import com.example.authservice.commons.dtos.TokenResponse;
import com.example.authservice.commons.dtos.UserRequest;
import com.example.authservice.commons.entities.UserModel;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.services.AuthService;
import com.example.authservice.services.JwtService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl  implements AuthService {
private final UserRepository userRepository;
private final JwtService jwtService;
// Esta clase implementa la lógica de autenticación y gestión de usuarios
    public AuthServiceImpl(UserRepository userRepository,JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
        .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }
    @Override
    public TokenResponse loginUser(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
                .filter(user -> user.getPassword().equals(userRequest.getPassword()))
                .map(user -> jwtService.generateToken((user.getId())))
                .orElseThrow(() -> new RuntimeException("Error credentials"));
    }
    // Método  para mapear UserRequest a UserModel
    private UserModel mapToEntity(UserRequest userRequest) {
    return UserModel.builder()
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .role("USER")
            .build();

    }
}
