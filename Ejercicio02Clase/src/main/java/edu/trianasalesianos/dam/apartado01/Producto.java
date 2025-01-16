package edu.trianasalesianos.dam.apartado01;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double pvp;

    @OneToMany(mappedBy = "producto")
    private Categoria categoria;
}
