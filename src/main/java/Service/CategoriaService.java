package Service;

import Model.Categoria;
import Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAll(){

        List<Categoria> lista = categoriaRepository.findAll();

        if (lista.isEmpty()){
            throw new CategoryNotFoundException();
        }

        return lista;
    }

    public Categoria getCategoriaById(Long id){

        return categoriaRepository.findById(id)
                .orElseThrow(CategoryNotFoundException()::new);

    }




}
