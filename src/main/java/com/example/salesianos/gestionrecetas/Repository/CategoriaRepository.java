package com.example.salesianos.gestionrecetas.Repository;

import com.example.salesianos.gestionrecetas.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
