package edu.trianasalesianos.dam.ejemploHerencias.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Coche extends Vehiculo{

    private int numPlazas;
    private boolean descapotable;

}
