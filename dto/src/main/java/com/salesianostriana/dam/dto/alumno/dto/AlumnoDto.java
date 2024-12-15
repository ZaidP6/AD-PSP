package com.salesianostriana.dam.dto.alumno.dto;

import com.salesianostriana.dam.dto.alumno.model.Alumno;
import com.salesianostriana.dam.dto.alumno.model.Curso;
import com.salesianostriana.dam.dto.alumno.model.Direccion;

public record AlumnoDto(
        String nombre, String apellidos, String email, String curso, String direccion)
{

    public static AlumnoDto toAlumnoDto(Alumno alumno){
        String apellidos = alumno.getApellido1() + " " + alumno.getApellido2();
        String curso = alumno.getCurso().getNombre() + ", " + alumno.getCurso().getTipo();
        String direccion = alumno.getDireccion().getTipoVia() + " " + alumno.getDireccion().getLinea1()
                + ", " + alumno.getDireccion().getLinea2() + " " + alumno.getDireccion().getCp()
                + ", " + alumno.getDireccion().getPoblacion() + ", " + alumno.getDireccion().getProvincia();

        return new AlumnoDto(
                alumno.getNombre(),
                apellidos,
                alumno.getEmail(),
                curso,
                direccion
        );
    }

}
