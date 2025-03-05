package edu.trianasalesianos.dam.vizitable.user.repository;

import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, UUID> {

    boolean existsByNombre(String nombre);

    Page<Lugar> findByPaisIgnoreCase(String pais, Pageable pageable);

    boolean existsByNombreIgnoreCase(String nombre);
}
