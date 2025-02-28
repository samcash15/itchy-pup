package com.cashion.itchy_pup.controller;

import com.cashion.itchy_pup.domain.User;
import com.cashion.itchy_pup.dto.request.UserRegistrationRequest;
import com.cashion.itchy_pup.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "User successfully registered",
            content = @Content(schema = @Schema(implementation = User.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input or email already exists"
        )
    })
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
} 