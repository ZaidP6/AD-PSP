package edu.trianasalesianos.dam.apartado02;

import edu.trianasalesianos.dam.apartado01.Producto;
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
public class CursoOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double puntuacion;

    @OneToMany(mappedBy = "cursos")
    private Profesor profesor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Video> videos = new HashSet<>();
}
