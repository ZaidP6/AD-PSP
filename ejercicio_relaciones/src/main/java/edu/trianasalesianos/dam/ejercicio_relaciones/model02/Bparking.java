package edu.trianasalesianos.dam.ejercicio_relaciones.model02;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Ejemplo @OneToMany
 * Clase Mapped
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bparking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;

    @OneToMany(mappedBy = "parking",cascade = CascadeType.ALL) //si se destruye el parking no quedara registro de los coches aparcados
    @JoinColumn
    private List<Bvehiculo> vehiculos;
}
