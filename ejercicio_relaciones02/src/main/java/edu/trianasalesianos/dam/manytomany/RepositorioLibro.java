package edu.trianasalesianos.dam.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioLibro extends JpaRepository<Libro, Long> {

    public Libro findById(long id);

    public Libro findByNombre(String nombre);
}
