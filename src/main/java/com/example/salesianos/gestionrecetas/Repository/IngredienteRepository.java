package com.example.salesianos.gestionrecetas.Repository;

import com.example.salesianos.gestionrecetas.Model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
