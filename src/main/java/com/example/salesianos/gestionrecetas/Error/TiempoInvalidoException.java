package com.example.salesianos.gestionrecetas.Error;

public class TiempoInvalidoException extends RuntimeException{

    public TiempoInvalidoException() {
        super("El tiempo de preparación debe ser mayor que cero");
    }
}
