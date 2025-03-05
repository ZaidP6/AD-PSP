package edu.trianasalesianos.dam.vizitable.user.controller;

import edu.trianasalesianos.dam.vizitable.user.dto.user.EstadoSugeridoDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.EstadoSugeridoResponseDTO;
import edu.trianasalesianos.dam.vizitable.user.service.EstadoSugeridoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/estado-sugerido")
@RequiredArgsConstructor
public class EstadoSugeridoController {

    private final EstadoSugeridoService estadoSugeridoService;


    @Operation(
            summary = "Sugerir cambio de estado de un lugar",
            description = "Permite a un usuario autenticado sugerir un cambio de estado para un lugar específico.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Estado Sugerido"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sugerencia creada con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "uuid": "ab76a357-4963-4067-9d79-93f9e08daa71",
                                "estadoSugerido": "Clausurado",
                                "fechaInicio": "2025-04-01",
                                "fechaFin": null,
                                "comentario": "Hay obras por motivo de los juegos Olímpicos pero solo por dentro.",
                                "aprobado": false
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Lugar no encontrado"),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @PostMapping("/sugerir/{lugarId}")
    public ResponseEntity<EstadoSugeridoResponseDTO> sugerirCambio(
            @PathVariable @Parameter(description = "ID del lugar al que se sugiere el cambio",
                    example = "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67") UUID lugarId,
            @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la sugerencia",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                {
                                    "estadoSugerido": "Clausurado",
                                    "fechaInicio": "2025-04-01",
                                    "fechaFin": "",
                                    "comentario": "Hay obras por motivo de los juegos Olímpicos pero solo por dentro."
                                }
                                """))
            ) EstadoSugeridoDTO dto) {

        EstadoSugeridoResponseDTO responseDTO = estadoSugeridoService.sugerirCambio(lugarId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }



}
