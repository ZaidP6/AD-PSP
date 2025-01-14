package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Ejemplo @ManyToMany
 * Clase mapped
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;

    //@JoinTable(name = "estacion_ruta") ver cómo indicar la nueva tabla
    @ManyToMany(mappedBy = "estacion")
    private List<Bruta> rutas;
}
