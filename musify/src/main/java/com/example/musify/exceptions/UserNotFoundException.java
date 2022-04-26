package com.example.musify.exceptions;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException() {
        super("User was not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
