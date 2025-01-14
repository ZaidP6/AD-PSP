package com.salesianostriana.dam.data.controller;

import com.salesianostriana.dam.data.dto.EditProductoCmd;
import com.salesianostriana.dam.data.dto.GetProductoDto;
import com.salesianostriana.dam.data.service.ProductoService;
import com.salesianostriana.dam.data.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<GetProductoDto> getAll() {

        return productoService.findAll().
                stream().map(GetProductoDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetProductoDto getById(@PathVariable Long id) {

        return GetProductoDto.of(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GetProductoDto> save(@RequestBody EditProductoCmd producto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(GetProductoDto.of(productoService.save(producto)));
    }

    @PutMapping("/{id}")
    public GetProductoDto edit(@RequestBody EditProductoCmd producto, @PathVariable Long id) {

        return GetProductoDto.of(productoService.edit(producto, id));
    }


     @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        productoService.delete(id);

        return ResponseEntity.noContent().build();
     }
}
