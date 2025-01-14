package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import java.util.List;

/**
 * Ejemplo @ManyToMany
 */
public class Anoticia {

    private Long id;
    private String titular;
    private String cuerpo;
    private String autor;
    private List<String>fotos;

    List<Aperiodico> periodicos;
}
