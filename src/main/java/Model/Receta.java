package Model;

import jakarta.persistence.*;

@Entity
public class Receta {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private int tiempoPreparacion;

    private Enum dificultad;

    @ManyToMany
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}
