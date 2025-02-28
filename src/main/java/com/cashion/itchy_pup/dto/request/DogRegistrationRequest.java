package com.cashion.itchy_pup.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class DogRegistrationRequest {
    @NotBlank(message = "Dog name is required")
    private String name;

    @NotBlank(message = "Breed is required")
    private String breed;

    @Positive(message = "Age must be positive")
    private Integer age;

    @Positive(message = "Weight must be positive")
    private Double weight;

    private List<String> knownAllergies;

    private List<@Valid MedicationRequest> medications;

    @Valid
    private SymptomLogRequest currentSymptoms;
} 