package com.example.salesianos.gestionrecetas.Service;

import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Repository.CategoriaRepository;
import com.example.salesianos.gestionrecetas.Repository.RecetaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(RecetaNotFoundException()::new);

    }

    public Receta addReceta(RecetaDto dto){

        Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElse(null);

        Receta receta = dto.to(categoria);
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }




}
