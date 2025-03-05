package edu.trianasalesianos.dam.vizitable.user.model;

import edu.trianasalesianos.dam.vizitable.user.model.enumeraciones.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {


    @Id //@UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String email;

    @NaturalId
    @Column(unique = true)
    private String username;

    private String password;
    private String fullname;

    @Column(nullable = true)
    private String fotoPerfil;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private boolean enabled = false;

    private String activationToken;

    @Column(length = 32)
    private String secretKey;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @ManyToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<EstadoSugerido> listaSugerencias = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Lugar>lugaresFavoritos = new ArrayList<>();


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario = (Usuario) o;
        return getUuid() != null && Objects.equals(getUuid(), usuario.getUuid())
                && getUsername() != null && Objects.equals(getUsername(), usuario.getUsername());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(username);
    }
}
