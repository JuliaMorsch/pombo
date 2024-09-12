package com.example.pombo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NonBlankValidator implements ConstraintValidator<NonBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.trim().length() > 0;
    }
    
    
}
