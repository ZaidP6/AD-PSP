package edu.trianasalesianos.edu.pruebaEjercicioRelaciones;

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


    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Profesor profesor;

    @Builder.Default
    @OneToMany(mappedBy = "cursoOnline", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Video> videos = new HashSet<>();
}
