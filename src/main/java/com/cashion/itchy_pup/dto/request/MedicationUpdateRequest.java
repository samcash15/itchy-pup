package com.cashion.itchy_pup.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicationUpdateRequest {
    @NotBlank(message = "Medication name is required")
    private String name;
    
    private String dosage;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean discontinued;
} 