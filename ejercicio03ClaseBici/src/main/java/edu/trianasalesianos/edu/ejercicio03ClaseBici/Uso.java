package edu.trianasalesianos.edu.ejercicio03ClaseBici;

import jakarta.persistence.*;
import lombok.*;

import java.time.format.DateTimeParseException;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Uso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaInicio;
    private String fechaFin;
    private double coste;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.PERSIST,
                fetch = FetchType.EAGER)
    private Bicicleta bicicleta;


}
