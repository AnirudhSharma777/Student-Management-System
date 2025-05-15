package com.management.responseDto;

import lombok.Builder;

public record LoginResponseDto(
    String token,
    long expiresIn
) {

    @Builder
    public LoginResponseDto {}
}
