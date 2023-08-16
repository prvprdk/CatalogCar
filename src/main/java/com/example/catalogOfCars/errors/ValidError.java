package com.example.catalogOfCars.errors;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidError extends AppError {
    private final Map<String, String> errors;

    public ValidError(int statusCode, String message, LocalDateTime time, Map<String, String> errors) {
        super(statusCode, message, time);
        this.errors = errors;
    }

    public ValidError(Map<String, String> errors) {
        this.errors = errors;
    }
}
