package edu.trianasalesianos.edu.ejercicio03ClaseBici.repository;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioBicicleta extends JpaRepository<Bicicleta, Long> {

    public List<Bicicleta> findByMarca(String marca);

    public List<Bicicleta> findByEstado(boolean estado);

    public Long countByEstacionNombre(String nombre);

}
