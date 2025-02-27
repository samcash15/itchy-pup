package com.cashion.itchy_pup.dto.response;

import com.cashion.itchy_pup.domain.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Location location;
    // Note: We don't include password in the response
}