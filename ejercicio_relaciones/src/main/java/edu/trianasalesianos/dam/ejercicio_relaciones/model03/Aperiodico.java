package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import java.util.List;

/**
 * Ejemplo @ManyToMany
 */
public class Aperiodico {

    private Long id;
    private String nombre;

    private List<Anoticia> noticias;
}
