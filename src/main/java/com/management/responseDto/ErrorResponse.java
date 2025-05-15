package com.management.responseDto;

import lombok.Builder;

public record ErrorResponse(
    String errorCode,
    String errorMessage
) {

    @Builder
    public ErrorResponse {}
}
