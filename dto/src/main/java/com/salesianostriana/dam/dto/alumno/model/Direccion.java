package com.salesianostriana.dam.dto.alumno.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    private Long id;
    private String tipoVia;
    private String Linea1;
    private String linea2;
    private String cp;
    private String poblacion;
    private String provincia;
}
