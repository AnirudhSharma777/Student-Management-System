package com.management.responseDto;



public record ErrorResponse(
    String errorCode,
    String errorMessage
) {

}
