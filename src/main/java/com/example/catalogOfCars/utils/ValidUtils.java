package com.example.catalogOfCars.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidUtils {

    public Map<String, String> getErrorsValid(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError fieldError : bindingResult.getAllErrors()) {
            errors.put(fieldError.getObjectName(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
