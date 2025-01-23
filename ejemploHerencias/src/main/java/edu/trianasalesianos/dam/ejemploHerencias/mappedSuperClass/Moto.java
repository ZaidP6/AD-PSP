package edu.trianasalesianos.dam.ejemploHerencias.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
public class Moto extends Vehiculo{

    private String tipo;
    private String carnet;
}
