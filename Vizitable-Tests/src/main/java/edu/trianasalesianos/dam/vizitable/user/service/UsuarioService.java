package edu.trianasalesianos.dam.vizitable.user.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorException;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import edu.trianasalesianos.dam.vizitable.files.model.FileMetadata;
import edu.trianasalesianos.dam.vizitable.files.service.StorageService;
import edu.trianasalesianos.dam.vizitable.user.dto.admin.UsuarioListaDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.*;
import edu.trianasalesianos.dam.vizitable.user.error.MessageException;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Role;
import edu.trianasalesianos.dam.vizitable.user.repository.LugarRepository;
import edu.trianasalesianos.dam.vizitable.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final LugarRepository lugarRepository;
    private final StorageService storageService;


    private final String UPLOAD_DIR = "uploads/";

    // ======================== AUTENTICACIÓN ========================

    @Transactional
    public Usuario registrarUsuario(CrearUsuarioDto dto){

        validarRegistro(dto);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        Usuario nuevoUsuario = crearUsuario(dto, key.getKey());
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return  usuarioGuardado;
    }

    @Transactional
    public Usuario registrarAdmin(CrearUsuarioDto dto){

        validarRegistro(dto);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        Usuario nuevoUsuario = crearAdmin(dto, key.getKey());
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return  usuarioGuardado;
    }


    public boolean verificarCodigo2FA(Usuario usuario, int otpCode) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        try {
            return gAuth.authorize(usuario.getSecretKey(), otpCode);
        } catch (GoogleAuthenticatorException e) {
            System.out.println("Error al validar el código OTP: " + e.getMessage());
            return false;
        }
    }

    public Usuario autenticarUsuario(LoginRequest dto) {
        Usuario usuario = usuarioRepository.findFirstByUsername(dto.username())
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                        MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        if (!passwordEncoder.matches(dto.password(), usuario.getPassword())) {
            throw new MessageException(MessageException.ErrorType.PASSWORD_MISMATCH,
                    MessageException.ErrorType.PASSWORD_MISMATCH.getStatus());
        }

        if (!verificarCodigo2FA(usuario, dto.otpCode())) {
            throw new MessageException(MessageException.ErrorType.OTP_CODE_INVALID,
                    MessageException.ErrorType.OTP_CODE_INVALID.getStatus());
        }

        usuario.setEnabled(true);
        usuarioRepository.save(usuario);

        return usuario;
    }

    // ======================== PERFIL ========================

    @Transactional
    public PerfilResponseDTO verMiPerfil() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                        MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        Hibernate.initialize(usuario.getLugaresFavoritos());
        return PerfilResponseDTO.of(usuario);
    }

    @Transactional
    public PerfilResponseDTO actualizarPerfil(EditarDatosPersonalesDTO perfilDTO) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                        MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        usuario.setFullname(perfilDTO.fullname());
        usuario.setEmail(perfilDTO.email());

        usuarioRepository.save(usuario);
        return PerfilResponseDTO.of(usuario);
    }


    @Transactional
    public UsuarioImagenPerfilDTO actualizarImagenPerfil(MultipartFile file) {
        Usuario usuario = usuarioRepository.findFirstByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(() -> new MessageException(
                MessageException.ErrorType.USER_NOT_FOUND,
                MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        // Si ya tiene una imagen de perfil, eliminar la anterior del almacenamiento
        if (usuario.getFotoPerfil() != null) {
            storageService.delete(usuario.getFotoPerfil()); // Elimina la imagen antigua
        }

        // Guardamos la nueva imagen
        FileMetadata fileMetadata = storageService.store(file);

        // Asignamos la nueva imagen al usuario y guardamos los cambios
        usuario.setFotoPerfil(fileMetadata.getFilename());
        usuarioRepository.save(usuario);

        return new UsuarioImagenPerfilDTO(fileMetadata.getFilename());
    }


    // ======================== ADMINISTRACIÓN DE USUARIOS ========================

    public Page<UsuarioListaDTO> listarUsuarios(Pageable pageable) {
        Page<UsuarioListaDTO> usuarios = usuarioRepository.findAll(pageable).map(UsuarioListaDTO::of);

        if (usuarios.isEmpty()) {
            throw new MessageException(MessageException.ErrorType.NO_USERS_FOUND,
                    MessageException.ErrorType.NO_USERS_FOUND.getStatus());
        }
        return usuarios;
    }

    @Transactional
    public void eliminarUsuario(UUID id) {
        usuarioRepository.findById(id).ifPresent(usuarioRepository::delete);
    }

    @Transactional
    public Usuario registrarUsuarioPorAdmin(CrearUsuarioDto dto) {

        validarRegistro(dto);
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        Usuario nuevoUsuario = crearUsuario(dto, key.getKey());

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return usuarioGuardado;
    }

    // ======================== FAVORITOS ========================

    @Transactional
    public List<ListarLugarDTO> añadirFavorito(UUID lugarId) {
        Usuario usuario = usuarioRepository.findFirstByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        Lugar lugar = lugarRepository.findById(lugarId)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus()));

        if (usuario.getLugaresFavoritos().contains(lugar)) {
            throw new MessageException(MessageException.ErrorType.LUGAR_ALREADY_IN_FAVORITES,
                    MessageException.ErrorType.LUGAR_ALREADY_IN_FAVORITES.getStatus());
        }

        usuario.getLugaresFavoritos().add(lugar);
        usuarioRepository.save(usuario);

        return usuario.getLugaresFavoritos().stream()
                .map(ListarLugarDTO::of)
                .toList();
    }

    @Transactional
    public List<ListarLugarDTO> eliminarFavorito(UUID lugarId) {
        Usuario usuario = usuarioRepository.findFirstByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        Lugar lugar = lugarRepository.findById(lugarId)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus()));

        if (!usuario.getLugaresFavoritos().contains(lugar)) {
            throw new MessageException(MessageException.ErrorType.LUGAR_NOT_IN_FAVORITES,
                    MessageException.ErrorType.LUGAR_NOT_IN_FAVORITES.getStatus());
        }
        usuario.getLugaresFavoritos().remove(lugar);
        usuarioRepository.save(usuario);

        return usuario.getLugaresFavoritos().stream()
                .map(ListarLugarDTO::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<ListarLugarDTO> listarFavoritos(Pageable pageable) {
        Usuario usuario = usuarioRepository.findFirstByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(() -> new MessageException(MessageException.ErrorType.USER_NOT_FOUND,
                MessageException.ErrorType.USER_NOT_FOUND.getStatus()));

        List<ListarLugarDTO> favoritos = usuario.getLugaresFavoritos().stream()
                .map(ListarLugarDTO::of)
                .toList();

        if (favoritos.isEmpty()) {
            throw new MessageException(MessageException.ErrorType.FAVORITOS_EMPTY,
                    MessageException.ErrorType.FAVORITOS_EMPTY.getStatus());
        }
        return new PageImpl<>(favoritos, pageable, favoritos.size());
    }

    // ======================== MÉTODOS AUXILIARES ========================

    private void validarCampoExistente(Supplier<Boolean> existe, String valor, MessageException.ErrorType errorType) {
        if (valor.trim().isEmpty()) {
            log.warn("El campo no puede estar vacío");
            throw new MessageException(errorType, errorType.getStatus());
        }

        if (existe.get()) {
            log.warn("{} ya existe: {}", errorType.getMessage(), valor);
            throw new MessageException(errorType, errorType.getStatus());
        }
    }

    private void validarRegistro(CrearUsuarioDto dto) {
        validarCampoExistente(() -> usuarioRepository.existsByUsername(dto.username()),
                dto.username(),
                MessageException.ErrorType.USERNAME_EXISTS);

        validarCampoExistente(() -> usuarioRepository.existsByEmail(dto.email()),
                dto.email(),
                MessageException.ErrorType.EMAIL_EXISTS);

        if (!dto.password().equals(dto.verifyPassword())) {
            log.warn("Las contraseñas no coinciden");
            throw new MessageException(MessageException.ErrorType.PASSWORD_MISMATCH,
                    MessageException.ErrorType.PASSWORD_MISMATCH.getStatus());
        }
    }

    private Usuario crearUsuario(CrearUsuarioDto dto, String secretKey) {
        return Usuario.builder()
                .email(dto.email())
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.USER)
                .enabled(false)
                .secretKey(secretKey)
                .build();
    }

    private Usuario crearAdmin(CrearUsuarioDto dto, String secretKey) {
        return Usuario.builder()
                .email(dto.email())
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.ADMIN)
                .enabled(false)
                .secretKey(secretKey)
                .build();
    }
}
