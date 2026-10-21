package com.example.salesianos.gestionrecetas.Service;

import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Repository.CategoriaRepository;
import com.example.salesianos.gestionrecetas.Repository.IngredienteRepository;
import com.example.salesianos.gestionrecetas.Repository.RecetaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Data
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(RecetaNotFoundException()::new);

    }

    //Ayuda de la IA, no sabía como hacer para meter los ingredientes con las recetas.
    private Set<Ingrediente> buscarIngredientes(Set<Long> ids) {
        if (ids == null || ids.isEmpty()){
            return new HashSet<>();
        }

        return new HashSet<>(ingredienteRepository.findAllById(ids));
    }

    public Receta addReceta(RecetaDto dto){

        Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElse(null);

        return recetaRepository.save(dto.to(categoria,buscarIngredientes(dto.idingredientes())));
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    public Receta updateReceta(Long id,RecetaDto dto){
        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return recetaRepository.findById(id)
                .map(r->{
                    r.setNombre(dto.nombre());
                    r.setDificultad(dto.dificultad());
                    r.setTiempoPreparacionMin(dto.tiempoDePreparacion());
                    r.setCategoria(categoria);
                    return recetaRepository.save(r);
                }).orElse(null);

    }




}
