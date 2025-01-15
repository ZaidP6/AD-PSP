package edu.trianasalesianos.dam;

import edu.trianasalesianos.dam.manytomany.Autor;
import edu.trianasalesianos.dam.manytomany.Libro;
import edu.trianasalesianos.dam.manytomany.RepositorioAutor;
import edu.trianasalesianos.dam.manytomany.RepositorioLibro;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MainDeMentira {

        private final RepositorioLibro repoLibro;
        private final RepositorioAutor repoAutor;

        @PostConstruct
    public void run() {
            Autor autor01 = Autor.builder()
                    .nombre("Benito")
                    .apellidos("Pérez Galdós")
                    .build();
            repoAutor.save(autor01);

            Libro libro01 = Libro.builder()
                    .anyo(1998)
                    .titulo("Título libro")
                    .build();
            repoLibro.save(libro01);




    }


}
