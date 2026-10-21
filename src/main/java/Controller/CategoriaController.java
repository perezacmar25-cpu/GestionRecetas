package Controller;

import Dto.CategoriaDto;
import Model.Categoria;
import Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;



    @GetMapping
    public ResponseEntity<List<CategoriaDto>> getAll(){

        List<Categoria> lista = categoriaService.getAll();

        List<CategoriaDto> listadto = new ArrayList<>();

        for(Categoria categoria : lista){
        listadto.add(CategoriaDto.of(categoria));
        }

        return ResponseEntity.ok(listadto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> addCategoria(@RequestBody CategoriaDto categoriaDto){

        return ResponseEntity.status(201)
                .body(CategoriaDto.of(categoriaService.crearCategoria(categoriaDto)));

    }

    @PutMapping
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id ,@RequestBody CategoriaDto categoria){

        return ResponseEntity.ok(
                CategoriaDto.of(categoriaService.updateCategoria(id,categoria))
        );
    }
}
