package com.cashion.itchy_pup.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private Double temperature;
    private Double humidity;
    private Double pollenCount;
    private String weatherCondition;
}