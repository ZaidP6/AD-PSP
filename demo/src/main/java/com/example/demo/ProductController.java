package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.add(product));
    }

}
