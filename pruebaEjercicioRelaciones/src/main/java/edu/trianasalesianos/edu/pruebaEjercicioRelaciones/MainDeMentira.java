package edu.trianasalesianos.edu.pruebaEjercicioRelaciones;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

    public final RepositorioProfesor repoProfe;
    public final RepositorioCursoOnline repoCurso;
    public final RepositorioVideo repoVideo;

    @PostConstruct
    public void run(){

        Profesor p1 = Profesor.builder()
                .nombre("Juan")
                .email("jdfsbskjdfb@gmail.com")
                .puntuacion(9.6)
                .build();

        repoProfe.save(p1);

        Video video = Video.builder()
                .titulo("Video01")
                .orden(1)
                .url("fgdjursfaksg")
                .descripcion("gghlrgrkdrgjsgjisdjgdñjg")
                .build();

        repoVideo.save(video);

        Video video2 = Video.builder()
                .titulo("Video02")
                .orden(2)
                .url("fgdjursfaksg")
                .descripcion("gghlrgrkdrgjsgjisdjgdñjg")
                .build();

        repoVideo.save(video);

        Profesor p = Profesor.builder()
                .nombre("Pepe")
                .email("rgndrgnn@gmail.com")
                .puntuacion(7.8)
                .build();

        repoProfe.save(p);

        CursoOnline curso = CursoOnline.builder()
                .nombre("curso01")
                .puntuacion(8.6)
                .profesor(p)
                .videos(Set.of(video, video2))
                .build();

        repoCurso.save(curso);

        CursoOnline curso02 = CursoOnline.builder()
                .nombre("curso02")
                .puntuacion(3.6)
                .profesor(p)
                .videos(Set.of(video, video2))
                .build();

        repoCurso.save(curso02);



    }

}