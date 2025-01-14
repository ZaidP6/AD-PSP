package com.salesianostriana.dam.data.controller;

import com.salesianostriana.dam.data.dto.EditCategoriaCmd;
import com.salesianostriana.dam.data.dto.EditProductoCmd;
import com.salesianostriana.dam.data.dto.GetCategoriaDto;
import com.salesianostriana.dam.data.dto.GetProductoDto;
import com.salesianostriana.dam.data.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<GetCategoriaDto> getAll(){
        return categoriaService.findAll()
                .stream().map(GetCategoriaDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id){
        return GetCategoriaDto.of(categoriaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public GetCategoriaDto edit(@RequestBody EditCategoriaCmd categoria,
                                @PathVariable Long id){

        return GetCategoriaDto.of(categoriaService.edit(categoria,id));
    }

    public ResponseEntity<GetCategoriaDto> save(@RequestBody GetCategoriaDto categoria, @PathVariable Long id){

        return GetCategoriaDto.of(categoriaService.edit(producto, id));
    }

}
