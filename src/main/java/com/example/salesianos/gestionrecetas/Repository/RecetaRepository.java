package com.example.salesianos.gestionrecetas.Repository;

import com.example.salesianos.gestionrecetas.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    boolean existsByNombre(String nombre);
}
