package com.example.musify.exceptions;

public class ArtistNotFoundException extends RuntimeException  {
    public ArtistNotFoundException() {}

    public ArtistNotFoundException(String message) {
        super(message);
    }
}
