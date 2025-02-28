package com.cashion.itchy_pup.dto.request;

import com.cashion.itchy_pup.domain.Symptom;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Data
public class SymptomLogRequest {
    @NotNull(message = "Itch level is required")
    @Min(value = 1, message = "Itch level must be between 1 and 10")
    @Max(value = 10, message = "Itch level must be between 1 and 10")
    private Integer itchLevel;

    private Set<Symptom> symptoms;
    private String notes;
} 