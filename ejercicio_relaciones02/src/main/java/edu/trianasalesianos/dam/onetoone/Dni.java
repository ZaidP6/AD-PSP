package edu.trianasalesianos.dam.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeros;
    private String letra;

    @OneToOne(mappedBy = "dni")
    @JoinColumn(name = "persona_id")
    private Persona persona;
}
