package edu.trianasalesianos.edu.ejercicio03ClaseBici;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Bicicleta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private boolean estado;

    @OneToMany(mappedBy = "bicicleta",
                cascade = CascadeType.PERSIST,
                orphanRemoval = true,
                fetch = FetchType.EAGER)
    private List<Uso>listaUsos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Estacion estacion;
}
