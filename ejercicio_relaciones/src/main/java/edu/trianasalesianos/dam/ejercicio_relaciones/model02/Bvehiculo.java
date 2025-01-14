package edu.trianasalesianos.dam.ejercicio_relaciones.model02;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ejemplo @ManyToOne
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bvehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Se podria poner numBastidor como id? Sin autogenerar.

    private String marca;
    private String modelo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bparking aparcamiento;
}
