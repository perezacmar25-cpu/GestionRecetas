package com.example.salesianos.gestionrecetas.Dto;

import com.example.salesianos.gestionrecetas.Model.Receta;

public record RecetaResumenDto(Long id, String nombre) {

    public static RecetaResumenDto of(Receta r) {

        return new RecetaResumenDto(r.getId(), r.getNombre());
    }
}
