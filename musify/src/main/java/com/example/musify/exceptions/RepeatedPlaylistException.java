package com.example.musify.exceptions;

public class RepeatedPlaylistException extends RuntimeException  {
    public RepeatedPlaylistException() {}

    public RepeatedPlaylistException(String message) {
        super(message);
    }
}
