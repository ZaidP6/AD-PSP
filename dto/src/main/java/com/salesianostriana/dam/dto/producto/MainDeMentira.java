package com.salesianostriana.dam.dto.producto;

import com.salesianostriana.dam.dto.producto.dto.ProductoDto;
import com.salesianostriana.dam.dto.producto.model.Categoria;
import com.salesianostriana.dam.dto.producto.model.Producto;

import java.util.Arrays;

public class MainDeMentira {

    public static void main(String[] args) {

        Categoria bebidas = Categoria.builder()
                .id(4L)
                .nombre("Bebidas")
                .build();

        Producto p = Producto.builder()
                .id(8L)
                .nombre("Zumo de Naranja")
                .desc("Bebida natural y refrescante, rica en vitamina C.")
                .pvp(2.50)
                .imagenes(Arrays.asList(
                        "https://upload.wikimedia.org/wikipedia/commons/9/9e/Orange_Juice.jpg",
                        "https://www.buenasalud.net/wp-content/uploads/2019/05/jugo-de-naranja.jpg"))
                .categoria(bebidas)
                .build();

        ProductoDto producto = ProductoDto.toProductoDto(p);

        System.out.println(producto);
    }

}
