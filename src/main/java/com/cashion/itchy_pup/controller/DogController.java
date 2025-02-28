package com.cashion.itchy_pup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.cashion.itchy_pup.dto.response.DogResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.cashion.itchy_pup.domain.Dog;
import com.cashion.itchy_pup.domain.User;
import com.cashion.itchy_pup.dto.request.DogRegistrationRequest;
import com.cashion.itchy_pup.service.DogService;
import com.cashion.itchy_pup.service.UserService;
import lombok.RequiredArgsConstructor;
import com.cashion.itchy_pup.dto.request.DogUpdateRequest;
import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@Tag(name = "Dog Management", description = "Endpoints for managing dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogService dogService;
    private final UserService userService;

    @Operation(
        summary = "Create a new dog",
        description = "Creates a new dog profile with the provided information"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Dog successfully created",
            content = @Content(schema = @Schema(implementation = DogResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input provided"
        )
    })
    @PostMapping
    public ResponseEntity<Dog> createDog(
        @RequestBody @Valid DogRegistrationRequest request,
        @RequestParam Long ownerId
    ) {
        User owner = userService.getUserById(ownerId);
        Dog dog = dogService.createDog(request, owner);
        return ResponseEntity.ok(dog);
    }

    @Operation(
        summary = "Get a dog by ID",
        description = "Retrieves a dog's information based on the provided ID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Dog found successfully",
            content = @Content(schema = @Schema(implementation = DogResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Dog not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DogResponse> getDog(
        @Parameter(description = "ID of the dog to retrieve")
        @PathVariable Long id
    ) {
        // Implementation
        return ResponseEntity.ok(new DogResponse());
    }

    @Operation(summary = "Update an existing dog")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Dog successfully updated",
            content = @Content(schema = @Schema(implementation = Dog.class))
        ),
        @ApiResponse(responseCode = "404", description = "Dog not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(
            @PathVariable Long id,
            @RequestBody @Valid DogUpdateRequest request) {
        Dog updatedDog = dogService.updateDog(id, request);
        return ResponseEntity.ok(updatedDog);
    }

    @GetMapping("/users/{userId}/dogs")
    public ResponseEntity<List<Dog>> getUserDogs(
            @Parameter(description = "ID of the user whose dogs to retrieve")
            @PathVariable Long userId) {
        return ResponseEntity.ok(dogService.getDogsByUserId(userId));
    }
}