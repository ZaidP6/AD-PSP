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
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni_id")
    private Dni dni;
}
