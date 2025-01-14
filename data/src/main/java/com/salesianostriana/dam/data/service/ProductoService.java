package com.salesianostriana.dam.data.service;

import com.salesianostriana.dam.data.dto.EditProductoCmd;
import com.salesianostriana.dam.data.model.Producto;
import com.salesianostriana.dam.data.repository.CategoriaRepository;
import com.salesianostriana.dam.data.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    public List<Producto> findAll() {

        List<Producto> result = productoRepository.findAll();

        if(result.isEmpty())
            throw new EntityNotFoundException();

        return result;
    }

    public Producto findById(Long id) {

        return productoRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("No hay un producto con el ID: %d".formatted(id))
                );
    }

    public Producto save(EditProductoCmd nuevo) {

        return productoRepository.save(
                Producto.builder()
                        .nombreProducto(nuevo.nombre())
                        .precioVenta(nuevo.precio())
                        .categoria(categoriaRepository.findById(nuevo.categoriaId()).orElse(null))
                        .descripcion(nuevo.descripcion())
                        .build()
        );
    }

    public Producto edit(EditProductoCmd producto, Long id) {

        return productoRepository.findById(id)
                .map(old -> {
                 old.setNombreProducto(producto.nombre());
                 old.setDescripcion(producto.descripcion());
                 old.setPrecioVenta(producto.precio());
                 old.setCategoria(categoriaRepository.findById(producto.categoriaId()).orElse(null));
                 return productoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay producto con el ID: %d".formatted(id)));
    }

    public void delete(Long id) {

        productoRepository.deleteById(id);
    }
}
