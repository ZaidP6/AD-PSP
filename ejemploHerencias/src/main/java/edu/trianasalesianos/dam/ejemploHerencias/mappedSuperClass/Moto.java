package edu.trianasalesianos.dam.ejemploHerencias.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
@SuperBuilder
public class Moto extends Vehiculo{

    private String tipo;
    private String carnet;
}
