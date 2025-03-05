package edu.trianasalesianos.dam.vizitable.user.controller;

import edu.trianasalesianos.dam.vizitable.security.jwt.access.JwtService;
import edu.trianasalesianos.dam.vizitable.security.jwt.refresh.RefreshToken;
import edu.trianasalesianos.dam.vizitable.security.jwt.refresh.RefreshTokenService;
import edu.trianasalesianos.dam.vizitable.user.dto.admin.CrearAdminDto;
import edu.trianasalesianos.dam.vizitable.user.dto.user.CrearUsuarioDto;
import edu.trianasalesianos.dam.vizitable.user.dto.user.LoginRequest;
import edu.trianasalesianos.dam.vizitable.user.dto.user.UsuarioResponse;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica a un usuario y genera tokens de acceso y actualización.",
            tags = {"Autenticación"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "id": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "user",
                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...",
                                "refreshToken": "a375f962-6c49-4ecd-8dc3-d29511435a5b"
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas o código OTP incorrecto",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "Credenciales incorrectas o código OTP inválido"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "Faltan datos obligatorios"
                            }
                            """)))
    })
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de acceso del usuario",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                        {
                            "username": "user",
                            "password": "Password123!",
                            "otpCode": "126383"
                        }
                        """))
    )
    public ResponseEntity<UsuarioResponse> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.autenticarUsuario(loginRequest);
        String accessToken = jwtService.generateAccessToken(usuario);
        RefreshToken refreshToken = refreshTokenService.create(usuario);

        return ResponseEntity.ok(UsuarioResponse.of(usuario, accessToken, refreshToken.getToken()));
    }



    @Operation(
            summary = "Refrescar el token de acceso",
            description = "Genera un nuevo token de acceso utilizando un token de actualización válido.",
            tags = {"Autenticación"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refrescado exitosamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "id": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "user",
                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...",
                                "refreshToken": "a375f962-6c49-4ecd-8dc3-d29511435a5b"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "El token de refresco no es válido o ha expirado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "Token de refresco inválido o expirado"
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "Usuario no autorizado"
                            }
                            """)))
    })
    @PostMapping("/refresh")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Token de actualización",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                        {
                            "refreshToken": "a375f962-6c49-4ecd-8dc3-d29511435a5b"
                        }
                        """))
    )
    public ResponseEntity<UsuarioResponse> refresh(@RequestParam String refreshToken) {
        UsuarioResponse usuarioResponse = refreshTokenService.refreshToken(refreshToken);
        return ResponseEntity.ok(usuarioResponse);
    }

    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea una cuenta de usuario con un nombre de usuario y correo electrónico únicos.",
            tags = {"Autenticación"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "id": "753b5d01-1060-4b4b-8685-7f1dc527dd42",
                                "username": "usuarioNuevo",
                                "secretKey": "6JTM6DQWRANNIBK5"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario existente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "El nombre de usuario ya existe"
                            }
                            """)))
    })
    @PostMapping("/register/")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para registrar un nuevo usuario",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                        {
                          "username": "usuarioNuevo",
                          "email": "usuario@email.com",
                          "password": "Password123!",
                          "verifyPassword": "Password123!"
                        }
                        """))
    )
    public ResponseEntity<UsuarioResponse> registroUsuario(
            @Valid @RequestBody CrearUsuarioDto dto) {

        Usuario nuevoUsuario = usuarioService.registrarUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.withSecret(nuevoUsuario, nuevoUsuario.getSecretKey()));
    }

    @Operation(
            summary = "Registrar un nuevo administrador",
            description = "Crea una cuenta de administrador con un nombre de usuario y correo electrónico únicos.",
            tags = {"Autenticación"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador registrado exitosamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "id": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
                                "username": "adminNuevo",
                                "secretKey": "ABCDEF123456"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario existente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "error": "El nombre de usuario ya existe"
                            }
                            """)))
    })
    @PostMapping("/register/admin")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para registrar un nuevo administrador",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                        {
                          "username": "adminNuevo",
                          "email": "admin@email.com",
                          "password": "Password123!",
                          "verifyPassword": "Password123!"
                        }
                        """))
    )
    public ResponseEntity<UsuarioResponse> registroAdmin(
            @Valid @RequestBody CrearUsuarioDto dto) {
        System.out.println("Recibida petición para registrar admin");

        Usuario nuevoAdmin = usuarioService.registrarAdmin(dto);

        System.out.println("Admin registrado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.withSecret(nuevoAdmin, nuevoAdmin.getSecretKey()));
    }




}
