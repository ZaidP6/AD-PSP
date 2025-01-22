package edu.trianasalesianos.edu.ejercicio03ClaseBici.controller;


import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Bicicleta;
import edu.trianasalesianos.edu.ejercicio03ClaseBici.service.ServicioBicicleta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bicicletas")
public class ControladorBicicleta {

    private final ServicioBicicleta biciService;

    @GetMapping("/")
    public ResponseEntity<List<Bicicleta>> findAll(){
        return ResponseEntity.ok(
                biciService.findAll()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bicicleta> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                biciService.findById(id)
        );
    }

    @PostMapping("/")
    public ResponseEntity<Bicicleta> create (@RequestBody Bicicleta bici){
        biciService.create(Bicicleta.builder()
                .marca("Peugeot")
                .modelo("45R")
                .estado(false)
                .estacion(null)
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(bici);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bicicleta> update (@PathVariable Long id, @RequestBody Bicicleta bici){
        Bicicleta bicicleta = biciService.updateBici(id, Bicicleta.builder()
                .marca(bici.getMarca())
                .modelo(bici.getModelo())
                .estado(bici.isEstado())
                .estacion(bici.getEstacion())
                .build());

        return ResponseEntity.ok(bicicleta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        biciService.deleteBici(id);
        return ResponseEntity.noContent().build();
    }


}
