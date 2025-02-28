package com.cashion.itchy_pup.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;     
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "dogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private User owner;

    @Column(nullable = false)
    private String breed;

    private Integer age;
    
    private Double weight;

    @ElementCollection
    private List<String> knownAllergies = new ArrayList<>();

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<Medication> medications = new ArrayList<>();

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<SymptomLog> symptomLogs = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Dog{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", breed='" + breed + '\'' +
            ", age=" + age +
            ", weight=" + weight +
            '}';
    }
}   