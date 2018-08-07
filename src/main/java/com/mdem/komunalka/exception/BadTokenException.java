package com.mdem.komunalka.exception;

public class BadTokenException extends RuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
}
