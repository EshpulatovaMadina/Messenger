package com.example.messenger.config;
import com.example.messenger.exception.DataAlreadyExistsException;
import com.example.messenger.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataAlreadyExistsException.class)
    public ResponseEntity<String> dataAlreadyExists(DataAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<String> dataNotFound(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
