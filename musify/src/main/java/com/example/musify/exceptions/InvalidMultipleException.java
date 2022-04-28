package com.example.musify.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidMultipleException extends RuntimeException  {
    List<Exception> exceptions;

    public InvalidMultipleException(List<Exception> exceptions) {
        this.exceptions=exceptions;

    }

    public InvalidMultipleException(String message) {
        super(message);
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
