package com.salesianostriana.dam.dto.producto.dto;


import com.salesianostriana.dam.dto.producto.model.Producto;

public record ProductoDto(String nombre, double pvp, String imagen, String categoria){

    public static ProductoDto toProductoDto(Producto producto){

        return new ProductoDto(
                producto.getNombre(),
                producto.getPvp(),
                producto.getImagenes().get(0),
                producto.getCategoria().getNombre()
        );
    }

}