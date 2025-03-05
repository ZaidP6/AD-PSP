package edu.trianasalesianos.dam.vizitable.security.jwt.refresh;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID id;

    //@MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

    @Column(nullable = false)
    private Instant expireAt;

    @Builder.Default
    private Instant createdAt = Instant.now();

    public String getToken() {
        return id.toString();
    }
}
