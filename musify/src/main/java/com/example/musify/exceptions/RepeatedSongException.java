package com.example.musify.exceptions;

public class RepeatedSongException extends RuntimeException  {
    public RepeatedSongException() {}

    public RepeatedSongException(String message) {
        super(message);
    }
}
