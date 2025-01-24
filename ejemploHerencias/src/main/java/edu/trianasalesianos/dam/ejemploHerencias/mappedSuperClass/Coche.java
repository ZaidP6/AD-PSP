package edu.trianasalesianos.dam.ejemploHerencias.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class Coche extends Vehiculo{

    private int numPlazas;
    private boolean descapotable;

}
