package com.salesianostriana.dam.data.repository;

import com.salesianostriana.dam.data.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
