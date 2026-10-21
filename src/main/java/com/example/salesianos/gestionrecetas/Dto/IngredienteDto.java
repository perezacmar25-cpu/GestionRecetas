package com.example.salesianos.gestionrecetas.Dto;

import com.example.salesianos.gestionrecetas.Model.Ingrediente;

public record IngredienteDto(
        Long id,
        String nombre
) {

    public static IngredienteDto of(Ingrediente i) {
        return new IngredienteDto(
                i.getId(),
                i.getNombre());
    }

    public Ingrediente to(){
        Ingrediente i = new Ingrediente();
        i.setNombre(nombre);
        return i;
    }


}
