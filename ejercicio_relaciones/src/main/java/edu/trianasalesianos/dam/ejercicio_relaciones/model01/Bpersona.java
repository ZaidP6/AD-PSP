package edu.trianasalesianos.dam.ejercicio_relaciones.model01;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ejemplo @OneToOne
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bpersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private BdocumentoIdentidad dni;
}
