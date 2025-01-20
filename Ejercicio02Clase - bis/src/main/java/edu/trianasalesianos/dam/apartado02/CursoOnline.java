package edu.trianasalesianos.dam.apartado02;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CursoOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double puntuacion;


    @ManyToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private Profesor profesor;

    @Builder.Default
    @OneToMany(mappedBy = "cursoOnline", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Video> videos = new HashSet<>();

    //HELPERS

    public void addVideo(Video video){
        video.setCursoOnline(this);
        this.videos.add(video);
    }

    public void deleteVideo(Video video){
        this.videos.remove(video);
        video.setCursoOnline(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CursoOnline that = (CursoOnline) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
