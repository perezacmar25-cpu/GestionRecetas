package com.example.salesianos.gestionrecetas.Dto;

import com.example.salesianos.gestionrecetas.Model.Categoria;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoriaDto(
        Long id,

        @NotBlank
        String nombre,
        String descripcion,
        List<RecetaResumenDto> recetas) {

    public static CategoriaDto of(Categoria c) {
        List<RecetaResumenDto> recetas = c.getRecetas().stream()
                .map(RecetaResumenDto::of)
                .toList();
        return new CategoriaDto(c.getId(), c.getNombre(), c.getDescripcion(),recetas);
    }

    public Categoria to(){
        Categoria c = new Categoria();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        return c;
    }
}
