package com.management.responseDto;

import java.time.LocalDateTime;

import com.management.Entities.Address;


public record StudentResponseDto(
    String id,
    String firstname,
    String lastname,
    String email,
    String password,
    int age,
    Address address,
    LocalDateTime createdAt,
    LocalDateTime updatedAt

) {

    
}
