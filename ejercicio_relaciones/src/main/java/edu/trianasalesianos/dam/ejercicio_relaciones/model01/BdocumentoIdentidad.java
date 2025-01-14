package edu.trianasalesianos.dam.ejercicio_relaciones.model01;

import jakarta.persistence.*;

/**
 * Ejemplo @OneToOne
 */
public class BdocumentoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeros; //una unica cadena, list de string, list de Integer,...?
    private String letra;

    @OneToOne(mappedBy = "dni")
    @JoinColumn
    private Bpersona persona;
}
