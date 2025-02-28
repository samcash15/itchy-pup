package com.cashion.itchy_pup.controller;

import com.cashion.itchy_pup.domain.Medication;
import com.cashion.itchy_pup.dto.request.MedicationRequest;
import com.cashion.itchy_pup.dto.request.MedicationUpdateRequest;
import com.cashion.itchy_pup.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medications")
@Tag(name = "Medication Management", description = "Endpoints for managing medications")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;

    @Operation(summary = "Add medication to dog")
    @PostMapping("/dogs/{dogId}/medications")
    public ResponseEntity<Medication> addMedication(
            @Parameter(description = "ID of the dog to add medication to")
            @PathVariable Long dogId,
            @Valid @RequestBody MedicationRequest request) {
        return ResponseEntity.ok(medicationService.addMedication(dogId, request));
    }

    @Operation(summary = "Update medication")
    @PutMapping("/medications/{medicationId}")
    public ResponseEntity<Medication> updateMedication(
            @Parameter(description = "ID of the medication to update")
            @PathVariable("medicationId") Long medicationId,
            @Valid @RequestBody MedicationUpdateRequest request) {
        return ResponseEntity.ok(medicationService.updateMedication(medicationId, request));
    }

    @Operation(summary = "Delete medication")
    @DeleteMapping("/medications/{medicationId}")
    public ResponseEntity<Void> deleteMedication(
            @Parameter(description = "ID of the medication to delete")
            @PathVariable("medicationId") Long medicationId) {
        medicationService.deleteMedication(medicationId);
        return ResponseEntity.noContent().build();
    }
} 