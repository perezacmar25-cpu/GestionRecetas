package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Service.IngredienteService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ingrediente")
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

}




