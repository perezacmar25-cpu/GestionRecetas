package com.example.salesianos.gestionrecetas.Service;


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
                .orElseThrow(new RecetaNotFoundException());

        return receta.getIngredientes();

    }
}


