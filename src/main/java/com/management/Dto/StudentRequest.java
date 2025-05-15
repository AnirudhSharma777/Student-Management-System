package com.management.Dto;

import com.management.Entities.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
        String id,

        @NotNull(message = "Student firstname is required") 
        String firstname,

        @NotNull(message = "Student lastname is required") 
        String lastname,

        @NotNull(message = "Student email is required") 
        @Email(message = "Customer Email is not a valid email address") 
        String email,

        String password,

        int age,

        Address address

) {
}
