package com.example.demo.repository;

import com.example.demo.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private HashMap<Long, Product> products = new HashMap<>();

    @PostConstruct
    public void init() {
        add(Product.builder().id(1L).name("Laptop").price(1200.0).build());
        add(Product.builder().id(2L).name("Smartphone").price(800.0).build());
        add(Product.builder().id(3L).name("Headphones").price(150.0).build());
        add(Product.builder().id(4L).name("Monitor").price(300.0).build());
        add(Product.builder().id(5L).name("Keyboard").price(50.0).build());
    }

    public Product add(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Optional<Product> get(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getAll() {
        return List.copyOf(products.values());
    }

    public Optional<Product> edit(Long id, Product newValue) {
        return Optional.ofNullable(products.computeIfPresent(id, (k, v) -> {
            v.setName(newValue.getName());
            v.setPrice(newValue.getPrice());
            return v;
        }));
    }

    public void delete(Long id) {
        products.remove(id);
    }



}
