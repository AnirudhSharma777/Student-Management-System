package com.management.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull(message = "Student email is required") 
    @Email(message = "Customer Email is not a valid email address") 
    String email,
    String password
) {

}
