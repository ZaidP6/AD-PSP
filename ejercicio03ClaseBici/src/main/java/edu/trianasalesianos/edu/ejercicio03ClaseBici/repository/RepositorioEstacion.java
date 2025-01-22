package edu.trianasalesianos.edu.ejercicio03ClaseBici.repository;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstacion extends JpaRepository<Estacion,Long> {
}
