package edu.trianasalesianos.dam.vizitable.user.repository;

import edu.trianasalesianos.dam.vizitable.user.model.EstadoLugar;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstadoLugarRepository extends JpaRepository<EstadoLugar, UUID> {

    void deleteAllByLugar(Lugar lugar);
}
