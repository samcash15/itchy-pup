package com.cashion.itchy_pup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.cashion.itchy_pup.dto.request.DogRequest;
import com.cashion.itchy_pup.dto.response.DogResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/dogs")
@Tag(name = "Dog Management", description = "Endpoints for managing dogs")
public class DogController {

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
    public ResponseEntity<DogResponse> createDog(
        @Parameter(description = "Dog details for creation")
        @Valid @RequestBody DogRequest dogRequest
    ) {
        // Implementation
        return ResponseEntity.ok(new DogResponse());
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
}