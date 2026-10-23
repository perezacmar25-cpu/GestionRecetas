package com.example.salesianos.gestionrecetas.Error;

public class CategoriaRepetidaException extends RuntimeException{

    public CategoriaRepetidaException(String nombre) {
        super("Ya existe una categoría con el nombre " + nombre);
    }
}
