package com.salesianostriana.dam.data.dto;

import com.salesianostriana.dam.data.model.Categoria;

public record EditCategoriaCmd(
        String nombre,
        String descripcion,
        Long cantidad) {



    //Y si quisiera que me devolviera lantiad de productos
    // que hay en esa categoria?
}
