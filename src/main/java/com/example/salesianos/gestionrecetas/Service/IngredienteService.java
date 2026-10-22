package com.example.salesianos.gestionrecetas.Service;


import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Error.IngredienteNotFoundException;
import com.example.salesianos.gestionrecetas.Error.RecetaNotFoundException;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Repository.IngredienteRepository;
import com.example.salesianos.gestionrecetas.Repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor

public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final RecetaRepository recetaRepository;

    public List<Ingrediente> getAll(){

        List <Ingrediente> lista = ingredienteRepository.findAll();

        return lista;

    }
    public Set<Ingrediente> getByReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(()-> new RecetaNotFoundException(id));

        return receta.getIngredientes();

    }

    public Ingrediente getIngredienteById(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    public Ingrediente crearIngrediente(Long id, IngredienteDto dto) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));

        Ingrediente ingrediente = ingredienteRepository.save(dto.to());

        receta.getIngredientes().add(ingrediente);
        recetaRepository.save(receta);

        return ingrediente;
    }
}


