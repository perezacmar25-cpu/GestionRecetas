package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;


    @GetMapping("/{id}")
    public ResponseEntity<RecetaDto> getRecetaById(@PathVariable Long id) {
        return ResponseEntity.ok(RecetaDto.of(recetaService.getRecetaById(id)));
    }


    @PostMapping
    public ResponseEntity<RecetaDto> addReceta(@RequestBody RecetaDto dto) {

        Receta guardada = recetaService.addReceta(dto);

        return ResponseEntity.status(201).body(RecetaDto.of(guardada));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDto> updateReceta(@PathVariable Long id, @RequestBody RecetaDto dto) {

        return ResponseEntity.ok(
                RecetaDto.of(recetaService.updateReceta(id,dto))
        );
    }
}
