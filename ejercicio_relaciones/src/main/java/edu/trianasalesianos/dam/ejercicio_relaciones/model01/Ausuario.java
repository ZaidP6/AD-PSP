package edu.trianasalesianos.dam.ejercicio_relaciones.model01;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Ejemplo @OneToOne
 * Clase propietaria
 */
public class Ausuario {

    private Long id;
    private String username;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Aperfil perfil;
}
