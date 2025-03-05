package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import jakarta.validation.constraints.NotBlank;

public record RechazoSugerenciaDTO(
        @NotBlank(message = "Debe indicar el motivo del rechazo de la sugerencia")
        String comentario
) {}
