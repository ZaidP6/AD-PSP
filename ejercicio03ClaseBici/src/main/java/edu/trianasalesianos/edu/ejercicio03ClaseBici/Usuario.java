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
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String numTarjeta;
    private String pin;
    private double saldo;

    @OneToMany(mappedBy = "usuario",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<Uso>listaUsos = new ArrayList<>();




}
