package com.example.salesianos.gestionrecetas.Controller;

import com.example.salesianos.gestionrecetas.Dto.CategoriaDto;
import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;


    @GetMapping("/{id}")
    public ResponseEntity<RecetaDto> getRecetaById(@PathVariable Long id) {
        return ResponseEntity.ok(RecetaDto.of(recetaService.getRecetaById(id)));
    }

    @GetMapping("/api/v1/recetas")
    public ResponseEntity<List<RecetaDto>> getAll(){
        List<RecetaDto> listaDto = recetaService.getAll()
                .stream()
                .map(RecetaDto::of)
                .toList();
        return ResponseEntity.ok(listaDto);
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

    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<RecetaDto> anadirIngrediente(@PathVariable Long recetaId, @RequestBody IngredienteDto dto) {

        Receta receta = recetaService.anadirIngrediente(recetaId, dto.id());

        return ResponseEntity.status(201).body(RecetaDto.of(receta));
    }
}
