package com.cashion.itchy_pup.repository;

import com.cashion.itchy_pup.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
} 