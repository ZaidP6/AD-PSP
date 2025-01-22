package edu.trianasalesianos.edu.ejercicio03ClaseBici.repository;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioBicicleta extends JpaRepository<Bicicleta, Long> {
}
