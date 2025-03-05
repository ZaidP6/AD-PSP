package edu.trianasalesianos.dam.vizitable.user.dto.user;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoSugerido;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record EstadoSugeridoResponseDTO(
        UUID uuid,
        Estado estadoSugerido,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String comentario,
        Boolean aprobado,
        Boolean rechazado,
        UUID lugarId,
        String nombreLugar,
        UUID usuarioId,
        String nombreUsuario
) {
        public static EstadoSugeridoResponseDTO of(EstadoSugerido estado) {
                return new EstadoSugeridoResponseDTO(
                        estado.getUuid(),
                        estado.getEstadoSugerido(),
                        estado.getFechaInicio(),
                        estado.getFechaFin(),
                        estado.getComentario(),
                        estado.getAprobado(),
                        estado.getRechazado(),
                        estado.getLugar().getUuid(),
                        estado.getLugar().getNombre(),
                        estado.getUsuario().getUuid(),
                        estado.getUsuario().getUsername()
                );
        }
}