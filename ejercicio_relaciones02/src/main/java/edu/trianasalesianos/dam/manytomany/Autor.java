package edu.trianasalesianos.dam.manytomany;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;

    @ManyToMany
    @JoinTable(name = "autor_libros")
    @Builder.Default
    private Set<Libro> libros = new HashSet<>();
}
