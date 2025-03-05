package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Tipo;

import java.time.LocalDate;

public record LugarRequestDTO(
        Tipo tipo,
        String nombre,
        String pais,
        String provinciaEstado,
        String ciudad,
        String coordenadas,
        LocalDate fechaConstruccion
) {
}
