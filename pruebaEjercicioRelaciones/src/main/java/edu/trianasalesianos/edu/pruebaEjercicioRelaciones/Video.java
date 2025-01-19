package edu.trianasalesianos.edu.pruebaEjercicioRelaciones;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orden;
    private String titulo;
    private String descripcion;
    private String url;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CursoOnline cursoOnline;


}
