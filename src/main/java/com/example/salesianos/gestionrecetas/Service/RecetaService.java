package com.example.salesianos.gestionrecetas.Service;

import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Error.CategoriaNotFoundException;
import com.example.salesianos.gestionrecetas.Error.IngredienteNotFoundException;
import com.example.salesianos.gestionrecetas.Error.RecetaNotFoundException;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Repository.CategoriaRepository;
import com.example.salesianos.gestionrecetas.Repository.IngredienteRepository;
import com.example.salesianos.gestionrecetas.Repository.RecetaRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));

    }

    //Ayuda de la IA, no sabía como hacer para meter los ingredientes con las recetas.
    private Set<Ingrediente> buscarIngredientes(Set<Long> ids) {
        if (ids == null || ids.isEmpty()){
            return new HashSet<>();
        }

        return new HashSet<>(ingredienteRepository.findAllById(ids));
    }

    public Receta addReceta(RecetaDto dto){

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new CategoriaNotFoundException(dto.idCategoria()));

        return recetaRepository.save(dto.to(categoria,buscarIngredientes(dto.idingredientes())));
    }

    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                        .orElseThrow(() -> new CategoriaNotFoundException(id));

        recetaRepository.deleteById(id);
    }

    public Receta updateReceta(Long id,RecetaDto dto){
        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new CategoriaNotFoundException(dto.idCategoria()));

        return recetaRepository.findById(id)
                .map(r->{
                    r.setNombre(dto.nombre());
                    r.setDificultad(dto.dificultad());
                    r.setTiempoPreparacionMin(dto.tiempoDePreparacion());
                    r.setCategoria(categoria);
                    return recetaRepository.save(r);
                }).orElse(null);

    }

    public Receta anadirIngrediente(Long recetaId, Long ingredienteId) {
        Receta receta = getRecetaById(recetaId);

        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteId)
                .orElseThrow(() -> new IngredienteNotFoundException(ingredienteId));

        for (Ingrediente i : receta.getIngredientes()) {
            if (i.getId().equals(ingredienteId)) {
                throw new IngredienteAlredyAddedException(recetaId, ingredienteId);
            }
        }

        receta.getIngredientes().add(ingrediente);
        return recetaRepository.save(receta);
    }




}
