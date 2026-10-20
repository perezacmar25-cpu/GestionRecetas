package Dto;

import Model.Categoria;
import Model.Dificultad;
import Model.Receta;

public record RecetaDto(
        Long id,
        String nombre,
        Integer tiempoDePreparacion,
        Dificultad dificultad,
        Long idCategoria

) {

    public static RecetaDto of(Receta r){
        return new RecetaDto(
                r.getId(),
                r.getNombre(),
                r.getTiempoPreparacionMin(),
                r.getDificultad(),
                r.getCategoria().getId()
        );

    }

    public Receta to(Categoria categoria) {
        Receta r = new Receta();
        r.setDificultad(dificultad);
        r.setTiempoPreparacionMin(tiempoDePreparacion);
        r.setNombre(nombre);
        r.setCategoria(categoria);

        return r;
    }
}
