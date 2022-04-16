package com.example.musify.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        log.error("Illegal Argument: " + e.getMessage());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    protected ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException e) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage());
        log.error("Expired TOKEN: "+ e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException e){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage());
        log.error("Invalid Token:"+ e.getMessage() );
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidUserException.class)
    protected ResponseEntity<Object> handleInvalidUserException(InvalidUserException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e.getMessage());
        log.error("User with the given id was not found.");
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArtistException.class)
    protected ResponseEntity<Object> handleInvalidArtistException(InvalidArtistException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e.getMessage());
        log.error("Artist with the given id was not found.");
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSongException.class)
    protected ResponseEntity<Object> handleInvalidSongException(InvalidSongException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e.getMessage());
        log.error("Song with the given id was not found.");
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidAlternativeTitleException.class)
    protected ResponseEntity<Object> handleInvalidSongException(InvalidAlternativeTitleException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e.getMessage());
        log.error("The Alternative Title with the given id was not found.");
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAlbumException.class)
    protected ResponseEntity<Object> handleInvalidAlbumException(InvalidAlbumException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e.getMessage());
        log.error("The Album with the given id was not found.");
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
