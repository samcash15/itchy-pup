package com.cashion.itchy_pup.service;

import com.cashion.itchy_pup.domain.User;
import com.cashion.itchy_pup.dto.request.LoginRequest;
import com.cashion.itchy_pup.dto.request.UserRegistrationRequest;
import com.cashion.itchy_pup.dto.response.AuthResponse;
import com.cashion.itchy_pup.repository.UserRepository;
import lombok.RequiredArgsConstructor;  
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!userService.verifyPassword(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            "dummy-token" // TODO: Implement JWT token generation
        );
    }

    public AuthResponse register(UserRegistrationRequest request) {
        User user = userService.registerUser(request);
        return new AuthResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            "dummy-token" // TODO: Implement JWT token generation
        );
    }
} 