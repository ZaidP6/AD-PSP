package edu.trianasalesianos.dam.vizitable.user.model;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Tipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lugar {

    @Id //@UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String nombre;
    private String pais;
    private String provinciaEstado;
    private String ciudad;
    private String coordenadas;
    private LocalDate fechaConstruccion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lugar")
    @Builder.Default
    private List<EstadoLugar>listaEstados = new ArrayList<>();

    @ManyToMany(mappedBy = "lugaresFavoritos", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Usuario>listaUsuariosEnFavoritos = new ArrayList<>();

}
