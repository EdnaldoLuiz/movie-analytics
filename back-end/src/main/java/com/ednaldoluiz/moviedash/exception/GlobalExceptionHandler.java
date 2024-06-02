package com.ednaldoluiz.moviedash.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public final class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    public ErrorResponse handleException(Exception ex) {
        log.error("Ocorreu um erro em 'handleException': {}", ex);
        return new ErrorResponse("Erro interno", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public List<ErrorResponse> handleNullFieldException(MethodArgumentNotValidException ex) {
        log.error("Ocorreu um erro em 'handleNullFieldException': {}", ex);
        final List<ErrorResponse> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new ErrorResponse(error.getField(), error.getDefaultMessage()));
        });
        return errors;
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<String> handleIOExceptions(IOException e) {
        log.error("Ocorreu um erro em 'handleIOException': {}", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
