package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoLugar;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Tipo;

import java.time.LocalDate;

public record LugarEditDTO(
        Tipo tipo,
        String nombre,
        String pais,
        String provinciaEstado,
        String ciudad,
        String coordenadas,
        LocalDate fechaConstruccion,
        Estado estadoActual,
        LocalDate fechaInicioEstado,
        LocalDate fechaFinEstado
) {
    public static LugarEditDTO of(Lugar lugar) {
        EstadoLugar ultimoEstado = lugar.getListaEstados().isEmpty() ? null :
                lugar.getListaEstados().get(lugar.getListaEstados().size() - 1);

        return new LugarEditDTO(
                lugar.getTipo(),
                lugar.getNombre(),
                lugar.getPais(),
                lugar.getProvinciaEstado(),
                lugar.getCiudad(),
                lugar.getCoordenadas(),
                lugar.getFechaConstruccion(),
                (ultimoEstado != null) ? ultimoEstado.getEstado() : Estado.DESCONOCIDO,
                (ultimoEstado != null) ? ultimoEstado.getFechaInicio() : null,
                (ultimoEstado != null) ? ultimoEstado.getFechaFin() : null
        );
    }
}
