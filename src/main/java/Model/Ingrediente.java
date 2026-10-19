package Model;

import jakarta.persistence.*;

@Entity
public class Ingrediente {

    @Id @GeneratedValue
    private Long id;

    private String nombre;




}
