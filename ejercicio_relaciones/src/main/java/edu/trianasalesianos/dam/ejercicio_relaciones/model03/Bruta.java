package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Ejemplo @ManyToMany
 * Clase propietaria
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    private List<Bestacion> estaciones;


}
