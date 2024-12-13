package com.salesianostriana.dam.dto.alumno.dto;

import com.salesianostriana.dam.dto.alumno.model.Curso;
import com.salesianostriana.dam.dto.alumno.model.Direccion;

public record GetAlumnoListDto(
        String nombre, String apellidos, String email, Curso curso, Direccion direccion)
{

}
