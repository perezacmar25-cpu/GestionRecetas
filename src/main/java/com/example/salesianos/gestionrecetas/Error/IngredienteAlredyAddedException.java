package com.example.salesianos.gestionrecetas.Error;

public class IngredienteAlredyAddedException extends RuntimeException{

    public IngredienteAlredyAddedException(Long recetaId, Long ingredienteId) {
        super("El ingrediente " + ingredienteId + " ya está en la receta " + recetaId);
    }
}
