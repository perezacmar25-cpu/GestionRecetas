package com.example.salesianos.gestionrecetas.Error;

public class RecetaNombreDuplicadoException extends RuntimeException {

    public RecetaNombreDuplicadoException(String nombre) {
        super("Ya existe una receta con el nombre " + nombre);
    }
}