package com.example.salesianos.gestionrecetas.Service;
import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor

public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;


    public List<Ingrediente> getAll(){

        List <Ingrediente> lista = ingredienteRepository.findAll();

        return lista;

    }

    public Ingrediente crearIngrediente(IngredienteDto dto) {
        Ingrediente ingrediente = dto.to();

        return ingredienteRepository.save(ingrediente);
    }
}


