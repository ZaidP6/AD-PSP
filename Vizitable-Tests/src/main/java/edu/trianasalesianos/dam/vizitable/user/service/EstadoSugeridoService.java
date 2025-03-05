package edu.trianasalesianos.dam.vizitable.user.service;

import edu.trianasalesianos.dam.vizitable.user.dto.admin.EstadoSugeridoPendienteDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.admin.RechazoSugerenciaDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.EstadoSugeridoDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.EstadoSugeridoResponseDTO;
import edu.trianasalesianos.dam.vizitable.user.error.MessageException;
import edu.trianasalesianos.dam.vizitable.user.model.EstadoLugar;
import edu.trianasalesianos.dam.vizitable.user.model.EstadoSugerido;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import edu.trianasalesianos.dam.vizitable.user.repository.EstadoLugarRepository;
import edu.trianasalesianos.dam.vizitable.user.repository.EstadoSugeridoRepository;
import edu.trianasalesianos.dam.vizitable.user.repository.LugarRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstadoSugeridoService {

    private final EstadoSugeridoRepository estadoSugeridoRepository;
    private final LugarRepository lugarRepository;
    private final EstadoLugarRepository estadoLugarRepository;

    // ========================================
    // 📌 1️⃣ Gestión de Estados Sugeridos
    // ========================================

    @Transactional
    public EstadoSugeridoResponseDTO sugerirCambio(UUID lugarId, EstadoSugeridoDTO dto) {
        Usuario usuario = getUsuarioAutenticado();
        Lugar lugar = buscarLugarPorId(lugarId);

        verificarSugerenciaPendiente(lugar, usuario);

        EstadoSugerido estadoSugerido = crearEstadoSugerido(dto, lugar, usuario);
        estadoSugeridoRepository.save(estadoSugerido);

        return EstadoSugeridoResponseDTO.of(estadoSugerido);
    }

    @Transactional(readOnly = true)
    public Page<EstadoSugeridoPendienteDTO> obtenerSugerenciasPendientes(Pageable pageable) {
        return buscarSugerenciasPendientes(pageable)
                .map(EstadoSugeridoPendienteDTO::of);
    }

    @Transactional
    public EstadoSugeridoPendienteDTO aprobarSugerencia(UUID sugerenciaId) {
        EstadoSugerido sugerencia = buscarSugerenciaPorId(sugerenciaId);

        verificarSugerenciaNoRechazada(sugerencia);
        actualizarEstadoSugerencia(sugerencia);

        EstadoLugar nuevoEstado = crearNuevoEstadoLugar(sugerencia);
        sugerencia.getLugar().getListaEstados().add(nuevoEstado);

        estadoSugeridoRepository.save(sugerencia);
        lugarRepository.save(sugerencia.getLugar());

        return EstadoSugeridoPendienteDTO.of(sugerencia);
    }

    @Transactional
    public EstadoSugeridoPendienteDTO rechazarSugerencia(UUID sugerenciaId, RechazoSugerenciaDTO dto) {
        EstadoSugerido sugerencia = buscarSugerenciaPorId(sugerenciaId);

        verificarSugerenciaNoAprobada(sugerencia);
        rechazarSugerenciaEstado(sugerencia, dto);

        estadoSugeridoRepository.save(sugerencia);
        return EstadoSugeridoPendienteDTO.of(sugerencia);
    }

    // ========================================
    // 📌 2️⃣ Métodos Auxiliares
    // ========================================

    private Usuario getUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Lugar buscarLugarPorId(UUID lugarId) {
        return lugarRepository.findById(lugarId)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus()));
    }

    private void verificarSugerenciaPendiente(Lugar lugar, Usuario usuario) {
        boolean tieneSugerenciaPendiente = estadoSugeridoRepository
                .existsByLugarAndUsuarioAndAprobadoFalseAndRechazadoFalse(lugar, usuario);

        if (tieneSugerenciaPendiente) {
            throw new MessageException(MessageException.ErrorType.SUGERENCIA_DUPLICATE,
                    MessageException.ErrorType.SUGERENCIA_DUPLICATE.getStatus());
        }
    }

    private EstadoSugerido buscarSugerenciaPorId(UUID sugerenciaId) {
        return estadoSugeridoRepository.findById(sugerenciaId)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.SUGERENCIA_NOT_FOUND,
                        MessageException.ErrorType.SUGERENCIA_NOT_FOUND.getStatus()));
    }

    private EstadoSugerido crearEstadoSugerido(EstadoSugeridoDTO dto, Lugar lugar, Usuario usuario) {
        return EstadoSugerido.builder()
                .estadoSugerido(dto.estadoSugerido())
                .fechaInicio(dto.fechaInicio())
                .fechaFin(dto.fechaFin())
                .comentario(dto.comentario())
                .aprobado(false)
                .rechazado(false)
                .lugar(lugar)
                .usuario(usuario)
                .build();
    }

    private Page<EstadoSugerido> buscarSugerenciasPendientes(Pageable pageable) {
        return estadoSugeridoRepository.findByAprobadoFalse(pageable);
    }

    private void verificarSugerenciaNoRechazada(EstadoSugerido sugerencia) {
        if (sugerencia.getRechazado()) {
            throw new MessageException(MessageException.ErrorType.SUGERENCIA_REJECTED,
                    MessageException.ErrorType.SUGERENCIA_REJECTED.getStatus());
        }
    }

    private void actualizarEstadoSugerencia(EstadoSugerido sugerencia) {
        sugerencia.setAprobado(true);
        sugerencia.setRechazado(false);
    }

    private EstadoLugar crearNuevoEstadoLugar(EstadoSugerido sugerencia) {
        return EstadoLugar.builder()
                .estado(sugerencia.getEstadoSugerido())
                .fechaInicio(sugerencia.getFechaInicio())
                .fechaFin(sugerencia.getFechaFin())
                .lugar(sugerencia.getLugar())
                .build();
    }

    private void verificarSugerenciaNoAprobada(EstadoSugerido sugerencia) {
        if (sugerencia.getAprobado()) {
            throw new MessageException(MessageException.ErrorType.SUGERENCIA_ALREADY_APPROVED,
                    MessageException.ErrorType.SUGERENCIA_ALREADY_APPROVED.getStatus());
        }
    }

    private void rechazarSugerenciaEstado(EstadoSugerido sugerencia, RechazoSugerenciaDTO dto) {
        sugerencia.setRechazado(true);
        sugerencia.setAprobado(false);
        sugerencia.setComentario(dto.comentario());
    }
}
