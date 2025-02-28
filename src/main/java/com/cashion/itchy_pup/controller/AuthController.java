package com.cashion.itchy_pup.controller;

import com.cashion.itchy_pup.dto.request.LoginRequest;
import com.cashion.itchy_pup.dto.request.UserRegistrationRequest;
import com.cashion.itchy_pup.dto.response.AuthResponse;
import com.cashion.itchy_pup.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
} 