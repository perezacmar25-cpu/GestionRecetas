package com.example.salesianos.gestionrecetas.Service;

import com.example.salesianos.gestionrecetas.Dto.IngredienteDto;
import com.example.salesianos.gestionrecetas.Dto.RecetaDto;
import com.example.salesianos.gestionrecetas.Error.*;
import com.example.salesianos.gestionrecetas.Model.Categoria;
import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import com.example.salesianos.gestionrecetas.Model.Receta;
import com.example.salesianos.gestionrecetas.Repository.CategoriaRepository;
import com.example.salesianos.gestionrecetas.Repository.IngredienteRepository;
import com.example.salesianos.gestionrecetas.Repository.RecetaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta getRecetaById(Long id){

        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));

    }

    public List<Receta> getAll(){
        return recetaRepository.findAll();


    }

    //Ayuda de la IA, no sabía como hacer para meter los ingredientes con las recetas.
    private Set<Ingrediente> buscarIngredientes(Set<IngredienteDto> ingredientesDto) {

        Set<Ingrediente> resultado = new HashSet<>();

        if (ingredientesDto == null) {
            return resultado;
        }

        Set<Long> ids = new HashSet<>();
        for (IngredienteDto i : ingredientesDto) {
            if (i.id() != null) {
                ids.add(i.id());
            }
        }

        resultado.addAll(ingredienteRepository.findAllById(ids));
        return resultado;
    }
    private void comprobarTiempo(Integer tiempo) {
        if (tiempo == null || tiempo <= 0) {
            throw new TiempoInvalidoException();
        }
    }

    private void comprobarNombreNoRepetido(String nombre) {
        if (recetaRepository.existsByNombre(nombre)) {
            throw new RecetaNombreDuplicadoException(nombre);
        }
    }

    private void comprobarIngrediente(Receta receta, Long ingredienteId) {
        for (Ingrediente i : receta.getIngredientes()) {
            if (i.getId().equals(ingredienteId)) {
                throw new IngredienteAlredyAddedException(receta.getId(), ingredienteId);
            }
        }
    }

    public Receta addReceta(RecetaDto dto){

        comprobarTiempo(dto.tiempoDePreparacion());
        comprobarNombreNoRepetido(dto.nombre());

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new CategoriaNotFoundException(dto.idCategoria()));

        return recetaRepository.save(dto.to(categoria,buscarIngredientes(dto.ingredientes())));
    }

    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                        .orElseThrow(() -> new RecetaNotFoundException(id));

        recetaRepository.deleteById(id);
    }

    public Receta updateReceta(Long id,RecetaDto dto){

        comprobarTiempo(dto.tiempoDePreparacion());
        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new CategoriaNotFoundException(dto.idCategoria()));

        return recetaRepository.findById(id)
                .map(r->{
                    if (!r.getNombre().equals(dto.nombre())) {
                        comprobarNombreNoRepetido(dto.nombre());
                    }
                    r.setNombre(dto.nombre());
                    r.setDificultad(dto.dificultad());
                    r.setTiempoPreparacionMin(dto.tiempoDePreparacion());
                    r.setCategoria(categoria);
                    return recetaRepository.save(r);
                }).orElseThrow(() -> new RecetaNotFoundException(id));

    }

    public Receta anadirIngrediente(Long recetaId, Long ingredienteId) {
        Receta receta = getRecetaById(recetaId);

        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteId)
                .orElseThrow(() -> new IngredienteNotFoundException(ingredienteId));

        comprobarIngrediente(receta,ingredienteId);


        receta.getIngredientes().add(ingrediente);
        return recetaRepository.save(receta);
    }




}
