package com.example.salesianos.gestionrecetas.Error;

public class RecetaNotFoundException extends RuntimeException{

    public RecetaNotFoundException(Long id) {
        super("No existe la receta con id " + id);
    }
}
