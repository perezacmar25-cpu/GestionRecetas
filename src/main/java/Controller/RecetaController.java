package Controller;

import Dto.RecetaDto;
import Service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receta")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;


    public ResponseEntity<RecetaDto> getCategoriaById(Long id){
        return ResponseEntity.ok(RecetaDto.of(recetaService.getRecetaById(id)));
    }

}
