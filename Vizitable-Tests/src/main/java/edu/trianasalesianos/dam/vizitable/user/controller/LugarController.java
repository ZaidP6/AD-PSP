package edu.trianasalesianos.dam.vizitable.user.controller;

import edu.trianasalesianos.dam.vizitable.user.dto.basic.ListadoLugarDto;
import edu.trianasalesianos.dam.vizitable.user.dto.basic.LugarDetailDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.ListarLugarDTO;
import edu.trianasalesianos.dam.vizitable.user.error.MessageException;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.repository.LugarRepository;
import edu.trianasalesianos.dam.vizitable.user.service.LugarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lugares/")
@RequiredArgsConstructor
public class LugarController {

    private final LugarService lugarService;
    private final LugarRepository lugarRepository;


    @Operation(
            summary = "Obtener lista de lugares",
            description = "Devuelve una lista de lugares registrados en el sistema.",
            tags = {"Lugares"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lugares obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            [
                                {
                                    "uuid": "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67",
                                    "tipo": "Catedral",
                                    "nombre": "La Giralda",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "estadoActual": "Accesible_Completamente"
                                },
                                {
                                    "uuid": "a12b3c4d-5678-90ef-abc1-23456789def0",
                                    "tipo": "Torre",
                                    "nombre": "Torre del Oro",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "estadoActual": "Accesible_Completamente"
                                }
                            ]
                            """))),
            @ApiResponse(responseCode = "204", description = "No hay lugares registrados")
    })
    @GetMapping
    public ResponseEntity<Page<ListadoLugarDto>> obtenerLugares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ListadoLugarDto> lugares = lugarService.obtenerLugares(pageable);

        return lugares.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lugares);
    }


    @Operation(
            summary = "Obtener detalles de un lugar",
            description = "Devuelve los detalles de un lugar específico identificado por su UUID.",
            tags = {"Lugares"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles del lugar obtenidos con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "uuid": "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67",
                                "tipo": "Catedral",
                                "nombre": "La Giralda",
                                "pais": "España",
                                "provinciaEstado": "Sevilla",
                                "ciudad": "Sevilla",
                                "coordenadas": "37.3861,-5.9925",
                                "fechaConstruccion": "1198-01-01",
                                "listaEstados": null,
                                "listaUsuariosEnFavoritos": null
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "Lugar no encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<LugarDetailDTO> obtenerDetalleLugar(@PathVariable UUID id) {
        return ResponseEntity.ok(lugarService.obtenerDetalleLugar(id));
    }


    @GetMapping("/filtrar")
    public ResponseEntity<Page<ListarLugarDTO>> listarLugaresPorPais(
            @RequestParam(required = false) String pais,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ListarLugarDTO> lugares = lugarService.listarLugaresPorPais(pais, PageRequest.of(page, size));

        return lugares.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lugares);
    }
}
