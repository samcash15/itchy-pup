package com.cashion.itchy_pup.service;

import com.cashion.itchy_pup.domain.Dog;
import com.cashion.itchy_pup.domain.Medication;
import com.cashion.itchy_pup.dto.request.MedicationRequest;
import com.cashion.itchy_pup.dto.request.MedicationUpdateRequest;
import com.cashion.itchy_pup.exception.DogNotFoundException;
import com.cashion.itchy_pup.exception.MedicationNotFoundException;
import com.cashion.itchy_pup.repository.DogRepository;
import com.cashion.itchy_pup.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final DogRepository dogRepository;

    public Medication addMedication(Long dogId, MedicationRequest request) {
        Dog dog = dogRepository.findById(dogId)
            .orElseThrow(() -> new DogNotFoundException("Dog not found with id: " + dogId));

        Medication medication = new Medication();
        medication.setName(request.getName());
        medication.setDosage(request.getDosage());
        medication.setFrequency(request.getFrequency());
        medication.setStartDate(request.getStartDate());
        medication.setEndDate(request.getEndDate());
        medication.setDog(dog);

        return medicationRepository.save(medication);
    }

    public Medication updateMedication(Long id, MedicationUpdateRequest request) {
        Medication medication = medicationRepository.findById(id)
            .orElseThrow(() -> new MedicationNotFoundException("Medication not found with id: " + id));

        medication.setName(request.getName());
        medication.setDosage(request.getDosage());
        medication.setFrequency(request.getFrequency());
        medication.setStartDate(request.getStartDate());
        medication.setEndDate(request.getEndDate());

        if (request.isDiscontinued()) {
            medication.setEndDate(LocalDate.now());
        }

        return medicationRepository.save(medication);
    }

    public void deleteMedication(Long id) {
        Medication medication = medicationRepository.findById(id)
            .orElseThrow(() -> new MedicationNotFoundException("Medication not found with id: " + id));
        
        // Verify the medication belongs to a dog before deletion
        if (medication.getDog() == null) {
            throw new IllegalStateException("Medication is not associated with any dog");
        }
        
        medication.getDog().getMedications().remove(medication);
        medicationRepository.delete(medication);
    }
} 