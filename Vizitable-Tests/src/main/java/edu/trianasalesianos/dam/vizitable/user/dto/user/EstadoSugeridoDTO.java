package edu.trianasalesianos.dam.vizitable.user.dto.user;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record EstadoSugeridoDTO(
        UUID uuid,
        @NotNull(message = "El ID del lugar no puede ser nulo")
        UUID lugarId,

        @NotNull(message = "El estado sugerido no puede ser nulo")
        Estado estadoSugerido,

        @NotNull(message = "La fecha de inicio no puede ser nula")
        @FutureOrPresent(message = "La fecha de inicio debe ser en el presente o futuro")
        LocalDate fechaInicio,

        @FutureOrPresent(message = "La fecha de fin debe ser en el presente o futuro")
        LocalDate fechaFin,

        @NotBlank(message = "El comentario no puede estar vacío")
        @Size(min = 5, max = 255, message = "El comentario debe tener entre 5 y 255 caracteres")
        String comentario,

        Boolean aprobado,
        Boolean rechazado
) { }

