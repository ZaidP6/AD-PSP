package com.salesianostriana.dam.data.dto;

import com.salesianostriana.dam.data.model.Producto;

public record EditProductoCmd(
        String nombre,
        String descripcion,
        double precio,
        int categoriaId
) {}
