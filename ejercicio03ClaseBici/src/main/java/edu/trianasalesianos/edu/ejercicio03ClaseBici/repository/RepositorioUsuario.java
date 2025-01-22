package edu.trianasalesianos.edu.ejercicio03ClaseBici.repository;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuario extends JpaRepository<Usuario,Long> {
}
