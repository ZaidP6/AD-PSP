package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monumento {

    private Long id;
    private String codPais;
    private String nombrePais;
    private String latitud;
    private String longitud;
    private String nombreMon;
    private String descripcion;
    private String urlImagen;

}
