package edu.trianasalesianos.dam.vizitable.user.dto.basic;

import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Tipo;

import java.util.UUID;

public record ListadoLugarDto(

        UUID uuid,
        Tipo tipo,
        String nombre,
        String pais,
        String provinciaEstado,
        Estado estadoActual
) {

    public static ListadoLugarDto of(Lugar lugar) {
        return new ListadoLugarDto(
                lugar.getUuid(),
                lugar.getTipo(),
                lugar.getNombre(),
                lugar.getPais(),
                lugar.getProvinciaEstado(),
                obtenerEstadoActual(lugar)
        );
    }

    private static Estado obtenerEstadoActual(Lugar lugar) {
        return lugar.getListaEstados().isEmpty()
                ? Estado.DESCONOCIDO
                : lugar.getListaEstados().get(lugar.getListaEstados().size() - 1).getEstado();

    }
}
