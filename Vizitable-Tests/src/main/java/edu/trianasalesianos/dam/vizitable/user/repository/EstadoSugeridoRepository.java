package edu.trianasalesianos.dam.vizitable.user.repository;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoSugerido;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstadoSugeridoRepository extends JpaRepository<EstadoSugerido, UUID> {

    Page<EstadoSugerido> findByAprobadoFalse(Pageable pageable);

    boolean existsByLugarAndUsuarioAndAprobadoFalseAndRechazadoFalse(Lugar lugar, Usuario usuario);
}
