package com.management.Exceptions;

public class SignUpFailedException extends RuntimeException{

    public SignUpFailedException(String message) {
        super(message);
    }
}
