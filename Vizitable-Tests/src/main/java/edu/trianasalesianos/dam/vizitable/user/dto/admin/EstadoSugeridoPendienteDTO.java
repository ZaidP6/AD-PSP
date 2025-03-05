package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoSugerido;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;

import java.time.LocalDate;
import java.util.UUID;

public record EstadoSugeridoPendienteDTO(
        UUID id,
        Estado estadoSugerido,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String comentario,
        String nombreLugar,
        String usuarioUsername
) {
    public static EstadoSugeridoPendienteDTO of(EstadoSugerido estadoSugerido) {
        return new EstadoSugeridoPendienteDTO(
                estadoSugerido.getUuid(),
                estadoSugerido.getEstadoSugerido(),
                estadoSugerido.getFechaInicio(),
                estadoSugerido.getFechaFin(),
                estadoSugerido.getComentario(),
                estadoSugerido.getLugar().getNombre(),
                estadoSugerido.getUsuario().getUsername()
        );
    }
}

