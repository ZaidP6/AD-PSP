package edu.trianasalesianos.dam.ejercicio_relaciones.model02;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ejemplo @ManyToOne
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Areaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;

    @ManyToOne()
    @JoinColumn(name = "publicacion", nullable = false)
    private Apublicacion apublicacion;
}
