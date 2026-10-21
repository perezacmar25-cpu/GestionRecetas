package com.example.salesianos.gestionrecetas.Service;

import com.example.salesianos.gestionrecetas.Dto.CategoriaDto;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Repository.CategoriaRepository;
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

    public Categoria crearCategoria(CategoriaDto dto) {
       Categoria categoria = dto.to();

       return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id,CategoriaDto dto){
        return categoriaRepository.findById(id)
                .map(c ->{
                    c.setNombre(dto.nombre());
                    c.setDescripcion(dto.descripcion());
                    return categoriaRepository.save(c);
                })
                .orElse(null);

    }

    public void eliminarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }



}
