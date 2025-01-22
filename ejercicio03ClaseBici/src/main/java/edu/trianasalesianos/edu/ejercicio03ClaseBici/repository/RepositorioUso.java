package edu.trianasalesianos.edu.ejercicio03ClaseBici.repository;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Uso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUso extends JpaRepository<Uso,Long> {
}
