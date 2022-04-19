package com.example.musify.exceptions;

public class InvalidSongException extends RuntimeException  {
    public InvalidSongException() {}

    public InvalidSongException(String message) {
        super(message);
    }
}
