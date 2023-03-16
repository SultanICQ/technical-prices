package com.technical.prices.infra.config;

import com.technical.prices.domain.exception.PriceNotFoundException;
import com.technical.prices.domain.exception.InvalidDateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exception {
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity priceNotFound() {
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity invalidDate() {
        return ResponseEntity.badRequest().build();
    }
}
