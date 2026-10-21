package Controller;

import Dto.RecetaDto;
import Model.Categoria;
import Model.Receta;
import Service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receta")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;


    public ResponseEntity<RecetaDto> getCategoriaById(Long id) {
        return ResponseEntity.ok(RecetaDto.of(recetaService.getRecetaById(id)));
    }


    @PostMapping
    public ResponseEntity<RecetaDto> addReceta(@RequestBody RecetaDto dto) {

        Receta guardada = recetaService.addReceta(dto);

        return ResponseEntity.status(201).body(RecetaDto.of(guardada));

    }
}
