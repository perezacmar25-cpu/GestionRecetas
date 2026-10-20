package Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    @Builder.Default
    private List<Receta> recetas = new ArrayList<>();
}
