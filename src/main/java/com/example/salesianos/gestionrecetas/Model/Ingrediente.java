package com.example.salesianos.gestionrecetas.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    @Builder.Default
    private Set<Receta> recetas = new HashSet<>();
}
