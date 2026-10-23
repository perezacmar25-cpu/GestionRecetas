package com.example.salesianos.gestionrecetas.Validation;

import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Dificultad;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TiempoCoherenteValidator implements ConstraintValidator<TiempoCoherente, RecetaDto> {

    @Override
    public boolean isValid(RecetaDto dto, ConstraintValidatorContext context) {
        if (dto.dificultad() == null || dto.tiempoDePreparacion() == null) {
            return true;
        }
        return !(dto.dificultad() == Dificultad.FACIL && dto.tiempoDePreparacion() > 60);
    }
}