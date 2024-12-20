package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        /*
            1. OBtener del repositorio la lista de productos
            2. Si la lista esta vacia, devolver 404
            3. Si lista tiene productos, devoolcer 200 con la lista
        */
        List<Product> result = productRepository.getAll();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /*
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(required = false, value = "maxPrice", defaultValue = "-1") double max,
            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection
    ){

        List<Product> result = productRepository.query(max, sortDirection);
            if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(result);
    }
     */

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.add(product));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getById (@PathVariable Long id){
        return ResponseEntity.of(productRepository.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> setById(@PathVariable("id") Long productId, @RequestBody Product product){
        return ResponseEntity.of(productRepository.edit(productId, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id){
        productRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
