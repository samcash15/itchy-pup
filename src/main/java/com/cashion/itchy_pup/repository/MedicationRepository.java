package com.cashion.itchy_pup.repository;

import com.cashion.itchy_pup.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
} 