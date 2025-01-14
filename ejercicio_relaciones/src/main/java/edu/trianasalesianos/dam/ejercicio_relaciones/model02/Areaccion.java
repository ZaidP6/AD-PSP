package edu.trianasalesianos.dam.ejercicio_relaciones.model02;

import jakarta.persistence.Entity;
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

    private Long id;
    private String reaccion;
}
