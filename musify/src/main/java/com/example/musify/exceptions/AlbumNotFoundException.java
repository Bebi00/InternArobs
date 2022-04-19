package com.example.musify.exceptions;

public class AlbumNotFoundException extends RuntimeException  {
    public AlbumNotFoundException() {}

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
