package edu.trianasalesianos.dam.vizitable.user.model;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoSugerido {

    @Id //@UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Estado estadoSugerido;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String comentario;
    private Boolean aprobado;
    private Boolean rechazado;
    private String imagen;
    private String comentarioAdmin;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER)
    private Lugar lugar;
}
