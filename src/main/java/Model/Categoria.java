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

public class Categoria {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    private String descripcion;


    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<Receta> recetas = new ArrayList<>();


}
