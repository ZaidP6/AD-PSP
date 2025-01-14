package com.salesianostriana.dam.dto.producto.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Long id;
    private String nombre;
}
