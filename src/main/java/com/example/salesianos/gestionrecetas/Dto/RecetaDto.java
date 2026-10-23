package com.example.salesianos.gestionrecetas.Dto;

import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Model.Dificultad;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Model.Receta;

import java.util.Set;
import java.util.stream.Collectors;

public record RecetaDto(
        Long id,
        String nombre,
        Integer tiempoDePreparacion,
        Dificultad dificultad,
        Long idCategoria,
        Set<IngredienteDto> ingredientes

) {

    public static RecetaDto of(Receta r){
        Set<IngredienteDto> ingredientesDto = r.getIngredientes().stream()
                .map(IngredienteDto::of)
                .collect(Collectors.toSet());

        return new RecetaDto(
                r.getId(),
                r.getNombre(),
                r.getTiempoPreparacionMin(),
                r.getDificultad(),
                r.getCategoria().getId(),
                ingredientesDto
        );

    }

    public Receta to(Categoria categoria,Set<Ingrediente> ingredientes1) {
        Receta r = new Receta();
        r.setDificultad(dificultad);
        r.setTiempoPreparacionMin(tiempoDePreparacion);
        r.setNombre(nombre);
        r.setCategoria(categoria);
        r.setIngredientes(ingredientes1);

        return r;
    }


}
