package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Ejemplo @ManyToMany
 * Clase propietaria
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Anoticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titular;
    private String cuerpo;
    private String autor;
    private LocalDate fechaPublicacion;
    private List<String>fotos;

    @ManyToMany(mappedBy = "noticias")
    @JoinColumn()
    List<Aperiodico> periodicos;
}
