package com.example.musify.exceptions;

public class InvalidPlaylistException extends RuntimeException  {
    public InvalidPlaylistException(String message) {
        super(message);
    }

    public InvalidPlaylistException() {
    }
}
