package edu.trianasalesianos.edu.pruebaEjercicioRelaciones;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Profesor {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String email;
    private double puntuacion;


    @JoinColumn(name = "cursos")
    @OneToMany(mappedBy = "profesor")
    @Builder.Default
    @ToString.Exclude
    private Set<CursoOnline> cursos = new HashSet<>();

}
