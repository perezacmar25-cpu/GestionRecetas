package com.example.salesianos.gestionrecetas.Error;

public class IngredienteRepetidoException extends RuntimeException {

    public IngredienteRepetidoException(String nombre) {
        super("Ya existe un ingrediente con el nombre " + nombre);
    }
}