package com.example.pombo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = NonBlankValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonBlank {
    String message() default "O texto deve conter pelo menos 1 caractere que não seja espaço,";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
