package edu.trianasalesianos.dam.ejercicio_relaciones.model03;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Ejemplo @ManyToMany
 * Clase mapped
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Aperiodico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    //si se genera tabla intermedia, indicar mas cosas?
    //se suelen usar ambas estrategias juntas
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //si se elimina noticia se elimina en todos los periodicos
    private List<Anoticia> noticias;
}
