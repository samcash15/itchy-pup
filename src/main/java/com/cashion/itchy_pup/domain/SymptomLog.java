package com.cashion.itchy_pup.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "symptom_logs")
@Data
public class SymptomLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer itchLevel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Symptom> symptoms = new HashSet<>();

    @Embedded
    private WeatherData weatherData;

    private String notes;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
} 