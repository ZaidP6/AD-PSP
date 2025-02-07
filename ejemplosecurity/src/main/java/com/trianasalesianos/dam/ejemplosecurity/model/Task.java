package com.trianasalesianos.dam.ejemplosecurity.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Task {

    @Id @GeneratedValue
    private Long id;
    private String title;
}
