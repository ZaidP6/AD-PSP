package edu.trianasalesianos.dam.vizitable.user.service;

import edu.trianasalesianos.dam.vizitable.user.dto.admin.LugarEditDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.admin.LugarRequestDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.basic.ListadoLugarDto;
import edu.trianasalesianos.dam.vizitable.user.dto.basic.LugarDetailDTO;
import edu.trianasalesianos.dam.vizitable.user.dto.user.ListarLugarDTO;
import edu.trianasalesianos.dam.vizitable.user.error.MessageException;
import edu.trianasalesianos.dam.vizitable.user.model.EstadoLugar;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import edu.trianasalesianos.dam.vizitable.user.repository.EstadoLugarRepository;
import edu.trianasalesianos.dam.vizitable.user.repository.LugarRepository;
import edu.trianasalesianos.dam.vizitable.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LugarService {

    private final LugarRepository lugarRepository;
    private final EstadoLugarRepository estadoLugarRepository;
    private final UsuarioRepository usuarioRepository;

    // ===========================
    // 1️⃣ Gestión de Lugares
    // ===========================

    public Lugar crearLugar(LugarRequestDTO lugarDTO) {
        validarNombreLugar(lugarDTO.nombre());
        Lugar nuevoLugar = construirLugarDesdeDTO(lugarDTO);
        return lugarRepository.save(nuevoLugar);
    }

    @Transactional
    public LugarEditDTO editarLugar(UUID id, LugarEditDTO lugarDTO) {
        Lugar lugar = lugarRepository.findById(id)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus()));

        if (!lugar.getNombre().equalsIgnoreCase(lugarDTO.nombre().trim()) &&
                lugarRepository.existsByNombreIgnoreCase(lugarDTO.nombre().trim())) {
            throw new MessageException(MessageException.ErrorType.LUGAR_ALREADY_EXISTS,
                    MessageException.ErrorType.LUGAR_ALREADY_EXISTS.getStatus());
        }

        lugar.setTipo(lugarDTO.tipo());
        lugar.setNombre(lugarDTO.nombre().trim());
        lugar.setPais(lugarDTO.pais().trim());
        lugar.setProvinciaEstado(lugarDTO.provinciaEstado().trim());
        lugar.setCiudad(lugarDTO.ciudad().trim());
        lugar.setCoordenadas(lugarDTO.coordenadas().trim());
        lugar.setFechaConstruccion(lugarDTO.fechaConstruccion());

        if (!lugar.getListaEstados().isEmpty()) {
            EstadoLugar ultimoEstado = lugar.getListaEstados().get(lugar.getListaEstados().size() - 1);
            ultimoEstado.setEstado(lugarDTO.estadoActual());
            ultimoEstado.setFechaInicio(lugarDTO.fechaInicioEstado());
            ultimoEstado.setFechaFin(lugarDTO.fechaFinEstado());
        } else {

            EstadoLugar estadoPorDefecto = EstadoLugar.builder()
                    .estado(Estado.DESCONOCIDO)
                    .fechaInicio(LocalDate.now())
                    .fechaFin(null)
                    .lugar(lugar)
                    .build();
            lugar.getListaEstados().add(estadoPorDefecto);
        }

        Lugar lugarGuardado = lugarRepository.save(lugar);
        return LugarEditDTO.of(lugarGuardado);
    }

    @Transactional
    public void eliminarLugar(UUID id) {
        lugarRepository.findById(id).ifPresent(lugar -> {
            estadoLugarRepository.deleteAllByLugar(lugar);
            usuarioRepository.removeLugarFromAllUsuarios(lugar);

            lugarRepository.delete(lugar);
        });
    }

    public Page<ListadoLugarDto> obtenerLugares(Pageable pageable) {
        Page<Lugar> lugares = lugarRepository.findAll(pageable);
        return lugares.map(ListadoLugarDto::of);
    }

    @Transactional(readOnly = true)
    public LugarDetailDTO obtenerDetalleLugar(UUID id) {
        Lugar lugar = lugarRepository.findById(id)
                .orElseThrow(() -> new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus()));

        return LugarDetailDTO.of(lugar);
    }

    // ===========================
    // 2️⃣ Consultas
    // ===========================

    public Page<ListarLugarDTO> listarLugaresPorPais(String pais, Pageable pageable) {
        Page<Lugar> lugares;

        if (pais == null || pais.trim().isEmpty()) {
            lugares = lugarRepository.findAll(pageable);
        } else {
            lugares = lugarRepository.findByPaisIgnoreCase(pais, pageable);
            if (lugares.isEmpty()) {
                throw new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                        MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus());
            }
        }
        return lugares.map(ListarLugarDTO::of);
    }

    // ===========================
    // 3️⃣ Validaciones
    // ===========================

    private void validarNombreLugar(String nombreLugar) {
        if (nombreLugar == null || nombreLugar.trim().isEmpty()) {
            throw new MessageException(MessageException.ErrorType.LUGAR_NOT_FOUND,
                    MessageException.ErrorType.LUGAR_NOT_FOUND.getStatus());
        }

        if (lugarRepository.existsByNombreIgnoreCase(nombreLugar.trim())) {
            throw new MessageException(MessageException.ErrorType.LUGAR_ALREADY_EXISTS,
                    MessageException.ErrorType.LUGAR_ALREADY_EXISTS.getStatus());
        }
    }

    // ===========================
    // 4️⃣ Métodos Auxiliares
    // ===========================

    private Lugar construirLugarDesdeDTO(LugarRequestDTO lugarDTO) {
        return Lugar.builder()
                .tipo(lugarDTO.tipo())
                .nombre(lugarDTO.nombre())
                .pais(lugarDTO.pais())
                .provinciaEstado(lugarDTO.provinciaEstado())
                .ciudad(lugarDTO.ciudad())
                .coordenadas(lugarDTO.coordenadas())
                .fechaConstruccion(lugarDTO.fechaConstruccion())
                .build();
    }







}
