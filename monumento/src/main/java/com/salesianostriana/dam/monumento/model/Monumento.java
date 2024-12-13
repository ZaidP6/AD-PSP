package com.salesianostriana.dam.monumento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Monumento {
    private Long id;
    private String codPais;
    private String nombrePais;
    private String nombreCiudad;
    private String latitud;
    private String longitud;
    private String nombreMon;
    private String descripcion;
    private String urlImagen;
}
