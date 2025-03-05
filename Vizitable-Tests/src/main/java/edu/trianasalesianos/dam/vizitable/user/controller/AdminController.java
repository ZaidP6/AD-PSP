package edu.trianasalesianos.dam.vizitable.user.controller;

import edu.trianasalesianos.dam.vizitable.user.dto.admin.*;
import edu.trianasalesianos.dam.vizitable.user.dto.user.CrearUsuarioDto;
import edu.trianasalesianos.dam.vizitable.user.dto.user.UsuarioResponse;
import edu.trianasalesianos.dam.vizitable.user.model.EstadoSugerido;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.service.EstadoSugeridoService;
import edu.trianasalesianos.dam.vizitable.user.service.LugarService;
import edu.trianasalesianos.dam.vizitable.user.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LugarService lugarService;
    private final EstadoSugeridoService estadoSugeridoService;
    private final UsuarioService usuarioService;

    //LUGAR

    @Operation(summary = "Crear un nuevo lugar", description = "Permite a un administrador crear un lugar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lugar creado correctamente", content = @Content(
                    schema = @Schema(implementation = Lugar.class),
                    examples = @ExampleObject(value = """
                    {
                        "uuid": "17244348-e7fe-4cde-a159-3aa32fa6f1b4",
                        "tipo": "Torre",
                        "nombre": "Torre Eiffel",
                        "pais": "Francia",
                        "provinciaEstado": "París",
                        "ciudad": "Paris",
                        "coordenadas": "37.1761, -3.5881",
                        "fechaConstruccion": "1238-01-01",
                        "listaEstados": null,
                        "listaUsuariosEnFavoritos": null
                    }
                """)
            )),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lugares/crear")
    public ResponseEntity<Lugar> crearLugar(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del lugar a crear",
            required = true, content = @Content(
            schema = @Schema(implementation = LugarRequestDTO.class),
            examples = @ExampleObject(value = """
                        {
                            "tipo": "Torre",
                            "nombre": "Torre Eiffel",
                            "pais": "Francia",
                            "provinciaEstado": "París",
                            "ciudad": "Paris",
                            "coordenadas": "37.1761, -3.5881",
                            "fechaConstruccion": "1238-01-01"
                        }
                    """)
    )) @RequestBody LugarRequestDTO lugarDTO) {
        Lugar nuevoLugar = lugarService.crearLugar(lugarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLugar);
    }

    @Operation(summary = "Editar un lugar", description = "Permite a un administrador actualizar los datos de un lugar existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lugar actualizado correctamente", content = @Content(
                    schema = @Schema(implementation = LugarEditDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "uuid": "17244348-e7fe-4cde-a159-3aa32fa6f1b4",
                  "tipo": "Puente",
                  "nombre": "San Telmo",
                  "pais": "Francia",
                  "provinciaEstado": "Île-de-France",
                  "ciudad": "París",
                  "coordenadas": "48.8584, 2.2945",
                  "fechaConstruccion": "1887-01-28",
                  "estadoActual": "Accesible_Completamente",
                  "fechaInicioEstado": "2025-02-23",
                  "fechaFinEstado": "2025-12-31"
                }
            """)
            )),
            @ApiResponse(responseCode = "404", description = "Lugar no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LugarEditDTO> editarLugar(
            @Parameter(description = "ID del lugar a editar", required = true) @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del lugar",
                    required = true, content = @Content(
                    schema = @Schema(implementation = LugarEditDTO.class),
                    examples = @ExampleObject(value = """
                    {
                      "tipo": "Puente",
                      "nombre": "San Telmo",
                      "pais": "Francia",
                      "provinciaEstado": "Île-de-France",
                      "ciudad": "París",
                      "coordenadas": "48.8584, 2.2945",
                      "fechaConstruccion": "1887-01-28",
                      "estadoActual": "Accesible_Completamente",
                      "fechaInicioEstado": "2025-02-23",
                      "fechaFinEstado": "2025-12-31"
                    }
                """)
            )) @Valid @RequestBody LugarEditDTO lugarDTO) {

        LugarEditDTO lugarActualizado = lugarService.editarLugar(id, lugarDTO);

        return ResponseEntity.ok(lugarActualizado);
    }

    @Operation(
            summary = "Eliminar un lugar",
            description = "Permite a un administrador eliminar un lugar existente por su ID.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Lugares"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lugar eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Lugar no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLugar(@PathVariable UUID id) {
        lugarService.eliminarLugar(id);
        return ResponseEntity.ok("Lugar eliminado corectamente");
    }

    //-------------------------------------------------------------

    //ESTADO-SUGERIDO

    @Operation(
            summary = "Obtener sugerencias de estado pendientes",
            description = "Recupera una lista de sugerencias de estado que aún no han sido aprobadas o rechazadas.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Estado Sugerido"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sugerencias pendientes obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay sugerencias pendientes"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/estado-sugerido/pendientes")
    public ResponseEntity<Page<EstadoSugeridoPendienteDTO>> obtenerSugerenciasPendientes(
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<EstadoSugeridoPendienteDTO> sugerencias = estadoSugeridoService.obtenerSugerenciasPendientes(pageable);
        return sugerencias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sugerencias);
    }

    @Operation(
            summary = "Aprobar una sugerencia de estado",
            description = "Permite a un administrador aprobar una sugerencia de estado pendiente.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Estado Sugerido"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sugerencia aprobada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sugerencia no encontrada"),
            @ApiResponse(responseCode = "400", description = "La sugerencia ya fue aprobada o no es válida"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/estado-sugerido/aprobar/{estadoSugeridoId}")
    public ResponseEntity<EstadoSugeridoPendienteDTO> aprobarSugerencia(@PathVariable UUID estadoSugeridoId) {
        EstadoSugeridoPendienteDTO responseDTO = estadoSugeridoService.aprobarSugerencia(estadoSugeridoId);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(
            summary = "Rechazar una sugerencia de estado",
            description = "Permite a un administrador rechazar una sugerencia de estado pendiente, indicando un comentario con la razón del rechazo.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Estado Sugerido"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sugerencia rechazada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sugerencia no encontrada"),
            @ApiResponse(responseCode = "400", description = "La sugerencia ya fue rechazada o no es válida")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Comentario con la razón del rechazo",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RechazoSugerenciaDTO.class),
                    examples = @ExampleObject(
                            name = "Ejemplo de rechazo",
                            value = "{ \"comentario\": \"Este monumento no está clausurado\" }"
                    )
            )
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/estado-sugerido/rechazar/{estadoSugeridoId}")
    public ResponseEntity<EstadoSugeridoPendienteDTO> rechazarSugerencia(
            @PathVariable UUID estadoSugeridoId,
            @Valid @RequestBody RechazoSugerenciaDTO dto) {

        EstadoSugeridoPendienteDTO responseDTO = estadoSugeridoService.rechazarSugerencia(estadoSugeridoId, dto);
        return ResponseEntity.ok(responseDTO);
    }

    //------------------------------------------------
    
    //USUARIOS

    @Operation(
            summary = "Listar todos los usuarios",
            description = "Devuelve una lista de usuarios registrados en el sistema.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, se requiere rol ADMIN")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public ResponseEntity<Page<UsuarioListaDTO>> listarUsuarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioListaDTO> usuarios = usuarioService.listarUsuarios(pageable);

        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }


    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario del sistema por su ID. Solo accesible por administradores.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, se requiere rol ADMIN"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable UUID id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Permite a un administrador registrar un nuevo usuario en el sistema.",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "id": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "user",
                                "secretKey": "6JTM6DQWRANNIBK5"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Error en la validación de los datos",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "Las contraseñas no coinciden"
                            }
                            """))),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, se requiere rol ADMIN")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuarios/registrar-user")
    public ResponseEntity<UsuarioResponse> registrarUsuarioPorAdmin(
            @Valid @RequestBody CrearUsuarioDto dto) {

        Usuario nuevoUsuario = usuarioService.registrarUsuarioPorAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioResponse.withSecret(nuevoUsuario, nuevoUsuario.getSecretKey()));
    }




}
