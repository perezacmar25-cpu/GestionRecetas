package Service;

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

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(RecetaNotFoundException()::new);

    }



}
