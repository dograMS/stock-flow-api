package org.dogra.stockflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ModelAtrributeValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidMethodArgumentExcetpion(MethodArgumentNotValidException e) {
        Map<String, String> errosMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errosMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(errosMap, HttpStatus.BAD_REQUEST);
    }
}
