package edu.trianasalesianos.dam.vizitable.user.dto.user;

import edu.trianasalesianos.dam.vizitable.user.model.Lugar;

import java.util.UUID;

public record ListarLugarDTO(
        UUID uuid,
        String nombre,
        String pais,
        String provinciaEstado
) {
    public static ListarLugarDTO of(Lugar lugar) {
        return new ListarLugarDTO(
                lugar.getUuid(),
                lugar.getNombre(),
                lugar.getPais(),
                lugar.getProvinciaEstado()
        );
    }
}
