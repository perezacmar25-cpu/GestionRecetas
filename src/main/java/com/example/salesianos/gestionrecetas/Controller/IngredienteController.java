package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Service.IngredienteService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<IngredienteDto>> getAll() {

        List<IngredienteDto> lista = ingredienteService.getAll()
                .stream()
                .map(IngredienteDto::of)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<IngredienteDto> addIngrediente(@Valid @RequestBody IngredienteDto dto) {

        Ingrediente ingrediente = ingredienteService.crearIngrediente(dto);

        return ResponseEntity.status(201)
                .body(IngredienteDto.of(ingrediente));

    }

}




