package edu.trianasalesianos.dam.apartado02;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

    public final RepositorioProfesor repoProfe;
    public final RepositorioCursoOnline repoCurso;


    public void run(){
        Profesor p = Profesor.builder()
                .nombre("Pepe")
                .email("rgndrgnn@gmail.com")
                .puntuacion(7.8)
                .build();

        repoProfe.save(p);

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


    }

}
