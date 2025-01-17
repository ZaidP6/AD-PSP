package edu.trianasalesianos.dam.apartado02;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfesor extends JpaRepository<Profesor, Long> {

    public Profesor getByNombre(String nombre);
}
