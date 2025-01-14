package com.salesianostriana.dam.data.repository;

import com.salesianostriana.dam.data.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
