package com.cashion.itchy_pup.service;

import com.cashion.itchy_pup.domain.*;
import com.cashion.itchy_pup.dto.request.DogRegistrationRequest;
import com.cashion.itchy_pup.dto.request.DogUpdateRequest;
import com.cashion.itchy_pup.dto.request.MedicationRequest;
import com.cashion.itchy_pup.exception.DogNotFoundException;
import com.cashion.itchy_pup.repository.DogRepository;
import com.cashion.itchy_pup.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final MedicationRepository medicationRepository;
    private final UserService userService;

    public Dog createDog(DogRegistrationRequest request, User owner) {
        Dog dog = new Dog();
        dog.setName(request.getName());
        dog.setBreed(request.getBreed());
        dog.setAge(request.getAge());
        dog.setWeight(request.getWeight());
        dog.setKnownAllergies(request.getKnownAllergies());
        dog.setOwner(owner);

        // Add medications if provided
        if (request.getMedications() != null) {
            for (MedicationRequest medRequest : request.getMedications()) {
                Medication medication = new Medication();
                medication.setName(medRequest.getName());
                medication.setDosage(medRequest.getDosage());
                medication.setFrequency(medRequest.getFrequency());
                medication.setStartDate(medRequest.getStartDate());
                medication.setEndDate(medRequest.getEndDate());
                medication.setDog(dog);
                dog.getMedications().add(medication);
            }
        }

        // Add initial symptom log if provided
        if (request.getCurrentSymptoms() != null) {
            SymptomLog symptomLog = new SymptomLog();
            symptomLog.setDog(dog);
            symptomLog.setItchLevel(request.getCurrentSymptoms().getItchLevel());
            symptomLog.setSymptoms(request.getCurrentSymptoms().getSymptoms());
            symptomLog.setNotes(request.getCurrentSymptoms().getNotes());
            dog.getSymptomLogs().add(symptomLog);
        }

        return dogRepository.save(dog);
    }

    public Dog updateDog(Long dogId, DogUpdateRequest request) {
        Dog dog = dogRepository.findById(dogId)
            .orElseThrow(() -> new DogNotFoundException("Dog not found with id: " + dogId));

        // Update basic information if provided
        if (request.getName() != null) {
            dog.setName(request.getName());
        }
        if (request.getBreed() != null) {
            dog.setBreed(request.getBreed());
        }
        if (request.getAge() != null) {
            dog.setAge(request.getAge());
        }
        if (request.getWeight() != null) {
            dog.setWeight(request.getWeight());
        }
        if (request.getKnownAllergies() != null) {
            dog.setKnownAllergies(request.getKnownAllergies());
        }

        // Add new medications if provided
        if (request.getNewMedications() != null) {
            for (MedicationRequest medRequest : request.getNewMedications()) {
                Medication medication = new Medication();
                medication.setName(medRequest.getName());
                medication.setDosage(medRequest.getDosage());
                medication.setFrequency(medRequest.getFrequency());
                medication.setStartDate(medRequest.getStartDate());
                medication.setEndDate(medRequest.getEndDate());
                medication.setDog(dog);
                dog.getMedications().add(medication);
            }
        }

        // Add new symptom log if provided
        if (request.getNewSymptoms() != null) {
            SymptomLog symptomLog = new SymptomLog();
            symptomLog.setDog(dog);
            symptomLog.setItchLevel(request.getNewSymptoms().getItchLevel());
            symptomLog.setSymptoms(request.getNewSymptoms().getSymptoms());
            symptomLog.setNotes(request.getNewSymptoms().getNotes());
            dog.getSymptomLogs().add(symptomLog);
        }

        // Delete medications if requested
        if (request.getMedicationsToDelete() != null && !request.getMedicationsToDelete().isEmpty()) {
            Iterator<Medication> iterator = dog.getMedications().iterator();
            while (iterator.hasNext()) {
                Medication medication = iterator.next();
                if (request.getMedicationsToDelete().contains(medication.getId())) {
                    iterator.remove();
                    medicationRepository.delete(medication);
                }
            }
        }

        return dogRepository.save(dog);
    }

    public List<Dog> getDogsByUserId(Long userId) {
        User owner = userService.getUserById(userId);
        return owner.getDogs();
    }
} 