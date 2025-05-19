package com.management.responseDto;



public record LoginResponseDto(
    String token,
    long expiresIn
) {

}
