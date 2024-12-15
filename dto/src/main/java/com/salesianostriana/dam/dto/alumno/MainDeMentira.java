package com.salesianostriana.dam.dto.alumno;

import com.salesianostriana.dam.dto.alumno.dto.AlumnoDto;
import com.salesianostriana.dam.dto.alumno.model.Alumno;
import com.salesianostriana.dam.dto.alumno.model.Curso;
import com.salesianostriana.dam.dto.alumno.model.Direccion;

public class MainDeMentira {

    public static void main(String[] args) {

        Direccion direccion = Direccion.builder()
                .id(5L)
                .tipoVia("Calle")
                .linea1("Calle de Alcalá 50")
                .linea2("4ºC")
                .cp(28014)
                .poblacion("Madrid")
                .provincia("Madrid")
                .build();

        Curso curso = Curso.builder()
                .id(3L)
                .nombre("Primero")
                .tipo("Bachillerato")
                .tutor("Carlos López Martínez")
                .aula("101")
                .build();

        Alumno a = Alumno.builder()
                .id(7L)
                .nombre("Ana")
                .apellido1("Gómez")
                .apellido2("Rodríguez")
                .telefono("654123789")
                .email("ana.gomez@gmail.com")
                .direccion(direccion)
                .curso(curso)
                .build();

        AlumnoDto alumno = AlumnoDto.toAlumnoDto(a);

        System.out.println(alumno);
    }
}
