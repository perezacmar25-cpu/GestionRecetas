package com.example.salesianos.gestionrecetas.Error;

public class IngredienteNotFoundException extends RuntimeException{

    public IngredienteNotFoundException(Long id) {
        super("No existe el ingrediente con id " + id);
    }
}
