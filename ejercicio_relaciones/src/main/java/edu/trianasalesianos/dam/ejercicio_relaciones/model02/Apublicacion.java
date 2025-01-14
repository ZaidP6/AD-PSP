package edu.trianasalesianos.dam.ejercicio_relaciones.model02;

import jakarta.persistence.*;

import java.util.List;

/**
 * Ejemplo @OneToMany
 * Leer abajo
 */
public class Apublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;

    @OneToMany(mappedBy = "publicacion")//no deberia ser esta la clase propietaria?
    @JoinColumn()
    private List<Areaccion> likes;
}
