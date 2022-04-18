package com.example.musify.exceptions;

public class PlaylistNotFoundException extends RuntimeException  {
    public PlaylistNotFoundException(String message) {
        super(message);
    }

    public PlaylistNotFoundException() {
    }
}
