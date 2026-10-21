package Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Data
@RequiredArgsConstructor
public class Receta {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Integer tiempoPreparacionMin;

    private Dificultad dificultad;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
        name = "receta_ingrediente",
        joinColumns = @JoinColumn(name = "receta_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    @Builder.Default
    private Set<Ingrediente> ingredientes = new HashSet<>();


}
