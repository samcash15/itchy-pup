package com.cashion.itchy_pup.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.List;

@Data
public class DogUpdateRequest {
    private String name;
    private String breed;
    
    @Positive(message = "Age must be positive")
    private Integer age;

    @Positive(message = "Weight must be positive")
    private Double weight;

    private List<String> knownAllergies;
    private List<@Valid MedicationRequest> newMedications;
    private List<Long> medicationsToDelete;
    
    @Valid
    private SymptomLogRequest newSymptoms;
} 