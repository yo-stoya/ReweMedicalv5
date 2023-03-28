package com.example.rewemedicalv5.exceptions.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueSpecialtyNameValidator.class)
public @interface UniqueSpecialtyName {
    String message() default "Specialty already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
