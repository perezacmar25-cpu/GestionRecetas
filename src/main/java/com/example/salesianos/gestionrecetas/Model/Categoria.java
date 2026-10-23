package com.example.salesianos.gestionrecetas.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String descripcion;


    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Receta> recetas = new ArrayList<>();


}
