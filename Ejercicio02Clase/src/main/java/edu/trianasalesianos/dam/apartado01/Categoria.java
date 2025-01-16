package edu.trianasalesianos.dam.apartado01;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Categoria> subcategorias;
}
