package com.salesianostriana.dam.data;

import com.salesianostriana.dam.data.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PseudoMain {

    private final ProductoRepository repository;

    @PostConstruct
    public void init() {

    }

 }
