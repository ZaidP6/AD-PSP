package edu.trianasalesianos.dam.onetomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String provincia;

    @OneToMany
    @JoinColumn(name = "habitante_id")
    private List<Habitante> habitantes;
}
