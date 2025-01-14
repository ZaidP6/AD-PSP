package com.salesianostriana.dam.data.dto;

import com.salesianostriana.dam.data.model.Producto;

public record GetProductoDto(
        Long id,
        String nombre,
        double precio,
        GetCategoriaDto categoria
) {

    public static GetProductoDto of (Producto producto) {

        return new GetProductoDto(
                producto.getId(),
                producto.getNombreProducto(),
                producto.getPrecioVenta(),
                GetCategoriaDto.of(producto.getCategoria())
        );
    }
}
