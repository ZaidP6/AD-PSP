package edu.trianasalesianos.dam.vizitable.user.controller;

import edu.trianasalesianos.dam.vizitable.user.dto.user.EditarDatosPersonalesDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.ListarLugarDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.PerfilResponseDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.UsuarioImagenPerfilDTO;
import edu.trianasalesianos.dam.vizitable.user.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios/")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    //PERFIL

    @Operation(
            summary = "Obtener perfil del usuario autenticado",
            description = "Permite a un usuario autenticado obtener los detalles de su perfil.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Usuario"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil obtenido con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "uuid": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "user",
                                "fullname": null,
                                "email": "user@example.com"
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponseDTO> verMiPerfil() {
        PerfilResponseDTO perfil = usuarioService.verMiPerfil();
        return ResponseEntity.ok(perfil);
    }


    @Operation(
            summary = "Editar perfil del usuario autenticado",
            description = "Permite a un usuario autenticado actualizar su nombre completo y correo electrónico.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Usuario"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil actualizado con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "uuid": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "user",
                                "fullname": "Juan Pérez Fernández",
                                "email": "usuario@email.com"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados del perfil",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                    {
                        "fullname": "Juan Pérez Fernández",
                        "email": "usuario@email.com"
                    }
                    """))
    )
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/editar-perfil")
    public ResponseEntity<PerfilResponseDTO> editarPerfil(@Valid @RequestBody EditarDatosPersonalesDTO perfilDTO) {
        PerfilResponseDTO usuarioActualizado = usuarioService.actualizarPerfil(perfilDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/perfil/imagen")
    public ResponseEntity<UsuarioImagenPerfilDTO> actualizarImagenPerfil(@RequestPart("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(new UsuarioImagenPerfilDTO("No se ha subido ninguna imagen"));
        }

        UsuarioImagenPerfilDTO response = usuarioService.actualizarImagenPerfil(file);
        return ResponseEntity.ok(response);
    }


    //---------------------------------------------------------------------------

    //LUGAR

    @Operation(
            summary = "Añadir un lugar a favoritos",
            description = "Permite a un usuario autenticado agregar un lugar a su lista de favoritos.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Usuario"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lugar añadido a favoritos con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            [
                                {
                                    "uuid": "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67",
                                    "nombre": "La Giralda",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "imagenUrl": "http://localhost:8080/lugares/imagen/e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67"
                                },
                                {
                                    "uuid": "a12b3c4d-5678-90ef-abc1-23456789def0",
                                    "nombre": "Torre del Oro",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "imagenUrl": "http://localhost:8080/lugares/imagen/a12b3c4d-5678-90ef-abc1-23456789def0"
                                }
                            ]
                            """))),
            @ApiResponse(responseCode = "400", description = "Lugar ya está en favoritos o no existe"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/favoritos/{lugarId}")
    public ResponseEntity<List<ListarLugarDTO>> añadirFavorito(@PathVariable UUID lugarId) {
        return ResponseEntity.ok(usuarioService.añadirFavorito(lugarId));
    }


    @Operation(
            summary = "Eliminar un lugar de favoritos",
            description = "Permite a un usuario autenticado eliminar un lugar de su lista de favoritos.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Usuario"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lugar eliminado de favoritos con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            [
                                {
                                    "uuid": "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67",
                                    "nombre": "La Giralda",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "imagenUrl": "http://localhost:8080/lugares/imagen/e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67"
                                }
                            ]
                            """))),
            @ApiResponse(responseCode = "400", description = "El lugar no está en favoritos o no existe"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/favoritos/{lugarId}")
    public ResponseEntity<List<ListarLugarDTO>> eliminarFavorito(@PathVariable UUID lugarId) {
        return ResponseEntity.ok(usuarioService.eliminarFavorito(lugarId));
    }


    @Operation(
            summary = "Listar lugares favoritos",
            description = "Devuelve la lista de lugares favoritos del usuario autenticado.",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Usuario"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lugares favoritos obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            [
                                {
                                    "uuid": "e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67",
                                    "nombre": "La Giralda",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "imagenUrl": "http://localhost:8080/lugares/imagen/e30b4a7e-1234-4c1d-8c7f-79e2a10e1a67"
                                },
                                {
                                    "uuid": "a12b3c4d-5678-90ef-abc1-23456789def0",
                                    "nombre": "Torre del Oro",
                                    "pais": "España",
                                    "provinciaEstado": "Sevilla",
                                    "imagenUrl": "http://localhost:8080/lugares/imagen/a12b3c4d-5678-90ef-abc1-23456789def0"
                                }
                            ]
                            """))),
            @ApiResponse(responseCode = "204", description = "El usuario no tiene lugares favoritos"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/favoritos")
    public ResponseEntity<Page<ListarLugarDTO>> listarFavoritos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ListarLugarDTO> favoritos = usuarioService.listarFavoritos(pageable);

        return favoritos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(favoritos);
    }





//    @PostMapping("/foto-perfil")
//    public ResponseEntity<Usuario> actualizarFotoPerfil(@RequestParam("file") MultipartFile file) throws IOException {
//        Usuario usuarioActualizado = usuarioService.actualizarFotoPerfil(file);
//        return ResponseEntity.ok(usuarioActualizado);
//    }








}
