package com.ednaldoluiz.moviedash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MovieProcessingException extends RuntimeException {

    public MovieProcessingException(String message) {
        super(message);
    }

    public MovieProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}