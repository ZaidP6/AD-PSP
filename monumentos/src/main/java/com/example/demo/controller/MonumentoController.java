package com.example.demo.controller;

import com.example.demo.model.Monumento;
import com.example.demo.repository.MonumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monumento")
public class MonumentoController {

    @Autowired
    private final MonumentoRepository monumentoRepository;

    @GetMapping
    public ResponseEntity<List<Monumento>> getAll(){
        List<Monumento> lista = monumentoRepository.getAll();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumento> getById(@PathVariable Long id){
        return ResponseEntity.of(monumentoRepository.getMonumento(id));
    }

    @PostMapping
    public ResponseEntity<Monumento> create(@RequestBody Monumento m) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monumentoRepository.addMonumento(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumento> edit(@PathVariable Long id, @RequestBody Monumento m) {
        return ResponseEntity.of(monumentoRepository.edit(id, m)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        monumentoRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
