package Service;

import Dto.RecetaDto;
import Model.Categoria;
import Model.Receta;
import Repository.CategoriaRepository;
import Repository.RecetaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(RecetaNotFoundException()::new);

    }

    public Receta addReceta(RecetaDto dto){

        Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElse(null);

        Receta receta = dto.to(categoria);
        recetaRepository.save(receta);
    }




}
