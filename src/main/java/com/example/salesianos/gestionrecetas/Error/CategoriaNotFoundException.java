package com.example.salesianos.gestionrecetas.Error;

public class CategoriaNotFoundException extends RuntimeException{

    public CategoriaNotFoundException(Long id) {

        super("No existe la categoría con id " + id);
    }
}
