package com.salesianostriana.dam.data.dto;

import com.salesianostriana.dam.data.model.Categoria;

public record GetCategoriaDto(
        Long id, String nombre, int cantiadad
) {

    public static GetCategoriaDto of (Categoria categoria) {

        return new GetCategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getProductos().size()
        );
    }
}
