package com.example.musify.exceptions;

public class BandNotFoundException extends RuntimeException  {
    public BandNotFoundException(String message) {
        super(message);
    }

    public BandNotFoundException() {
    }
}
