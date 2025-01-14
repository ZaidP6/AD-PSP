package com.salesianostriana.dam.dto.alumno.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    private Long id;
    private String nombre;
    private String tipo;
    private String tutor;
    private String aula;

}
