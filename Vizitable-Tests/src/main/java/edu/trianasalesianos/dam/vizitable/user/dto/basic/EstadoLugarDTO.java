package edu.trianasalesianos.dam.vizitable.user.dto.basic;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoLugar;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;

import java.time.LocalDate;
import java.util.UUID;

public record EstadoLugarDTO(
        UUID uuid,
        Estado estado,
        LocalDate fechaInicio,
        LocalDate fechaFin
) {
    public static EstadoLugarDTO of(EstadoLugar estadoLugar) {
        return new EstadoLugarDTO(
                estadoLugar.getUuid(),
                estadoLugar.getEstado(),
                estadoLugar.getFechaInicio(),
                estadoLugar.getFechaFin()
        );
    }
}