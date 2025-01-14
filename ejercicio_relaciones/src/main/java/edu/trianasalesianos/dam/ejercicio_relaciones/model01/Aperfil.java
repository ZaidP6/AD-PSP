package edu.trianasalesianos.dam.ejercicio_relaciones.model01;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ejemplo @OneToOne
 * Clase Mapped
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Aperfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellidos;

    private String telefono;

    @OneToOne(mappedBy = "perfil")
    private Ausuario usuario;
}
