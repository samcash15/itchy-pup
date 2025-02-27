package com.cashion.itchy_pup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogResponse {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private Double weight;
}
