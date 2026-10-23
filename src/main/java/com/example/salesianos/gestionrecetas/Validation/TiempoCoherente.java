package com.example.salesianos.gestionrecetas.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TiempoCoherenteValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TiempoCoherente {

    String message() default "Una receta FACIL no puede superar los 60 minutos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}