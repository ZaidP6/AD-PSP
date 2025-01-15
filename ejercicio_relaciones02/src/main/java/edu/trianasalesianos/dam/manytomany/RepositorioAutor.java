package edu.trianasalesianos.dam.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor, Long> {

    public Autor findByNombre(String nombre);



}
