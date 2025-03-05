package edu.trianasalesianos.dam.vizitable.user.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import edu.trianasalesianos.dam.vizitable.user.dto.user.CrearUsuarioDto;
import edu.trianasalesianos.dam.vizitable.user.dto.user.LoginRequest;
import edu.trianasalesianos.dam.vizitable.user.error.MessageException;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private GoogleAuthenticator googleAuthenticator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private CrearUsuarioDto crearUsuarioDto;
    private LoginRequest loginRequest;
    private Usuario mockUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Datos de prueba para registrar usuario
        crearUsuarioDto = new CrearUsuarioDto("testUsername", "testEmail@example.com", "password", "password");

        // Datos de prueba para login
        loginRequest = new LoginRequest("testUsername", "password", 123456);

        // Usuario de prueba
        mockUsuario = new Usuario();
        mockUsuario.setUsername("testUsername");
        mockUsuario.setPassword("encodedPassword");
        mockUsuario.setSecretKey("secretKey");
    }

    /**
     * Test para registrar un usuario correctamente
     */
    @Test
    void testRegistrarUsuario() {
        // Arrange
        GoogleAuthenticatorKey mockKey = mock(GoogleAuthenticatorKey.class);
        when(mockKey.getKey()).thenReturn("secretKey");
        when(googleAuthenticator.createCredentials()).thenReturn(mockKey);
        when(usuarioRepository.existsByUsername(crearUsuarioDto.username())).thenReturn(false);
        when(usuarioRepository.existsByEmail(crearUsuarioDto.email())).thenReturn(false);

        Usuario mockUsuario = new Usuario();
        mockUsuario.setUsername(crearUsuarioDto.username());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(mockUsuario);

        // Act
        Usuario result = usuarioService.registrarUsuario(crearUsuarioDto);

        // Assert
        verify(usuarioRepository).save(any(Usuario.class));
        assertNotNull(result);
        assertEquals(crearUsuarioDto.username(), result.getUsername());
    }

    /**
     * Test para autenticar usuario correctamente
     */
    @Test
    void testAutenticarUsuarioCorrecto() {
        // Arrange
        when(usuarioRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(googleAuthenticator.authorize(anyString(), anyInt())).thenReturn(true);

        // Act
        Usuario result = usuarioService.autenticarUsuario(loginRequest);

        // Assert
        verify(usuarioRepository).findFirstByUsername(anyString());
        verify(passwordEncoder).matches(anyString(), anyString());
        verify(googleAuthenticator).authorize(anyString(), anyInt());
        assertEquals(mockUsuario.getUsername(), result.getUsername());
    }

    /**
     * Test para fallar cuando la contraseña es incorrecta
     */
    @Test
    void testAutenticarUsuarioConContraseñaIncorrecta() {
        // Arrange
        when(usuarioRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act & Assert
        assertThrows(MessageException.class, () -> usuarioService.autenticarUsuario(loginRequest));
    }

    /**
     * Test para fallar cuando el código OTP es incorrecto
     */
    @Test
    void testAutenticarUsuarioConCodigoOtpIncorrecto() {
        // Arrange
        when(usuarioRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(googleAuthenticator.authorize(anyString(), anyInt())).thenReturn(false);

        // Act & Assert
        assertThrows(MessageException.class, () -> usuarioService.autenticarUsuario(loginRequest));
    }
}
