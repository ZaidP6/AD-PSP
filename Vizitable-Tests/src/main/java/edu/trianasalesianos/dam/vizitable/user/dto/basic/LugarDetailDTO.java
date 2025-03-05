package edu.trianasalesianos.dam.vizitable.user.dto.basic;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Tipo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record LugarDetailDTO(
        UUID uuid,
        Tipo tipo,
        String nombre,
        String pais,
        String provinciaEstado,
        String ciudad,
        String coordenadas,
        LocalDate fechaConstruccion,
        List<EstadoLugarDTO> historialEstados
) {

    public static LugarDetailDTO of(edu.trianasalesianos.dam.vizitable.user.model.Lugar lugar) {
        return new LugarDetailDTO(
                lugar.getUuid(),
                lugar.getTipo(),
                lugar.getNombre(),
                lugar.getPais(),
                lugar.getProvinciaEstado(),
                lugar.getCiudad(),
                lugar.getCoordenadas(),
                lugar.getFechaConstruccion(),
                lugar.getListaEstados().stream().map(EstadoLugarDTO::of).toList()
        );
    }
}
