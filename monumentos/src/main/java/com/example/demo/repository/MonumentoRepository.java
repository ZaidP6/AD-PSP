package com.example.demo.repository;

import com.example.demo.model.Monumento;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MonumentoRepository {

    private final HashMap<Long, Monumento> listaMonumentos = new HashMap<>();

    @PostConstruct
    public void init(){
        addMonumento(Monumento.builder()
                .id(1L)
                .codPais("ES")
                .nombrePais("España")
                .nombreCiudad("Sevilla")
                .latitud("37.3860")
                .longitud("-5.9926")
                .nombreMon("La Giralda")
                .descripcion("La Giralda es la torre campanario de la catedral de Santa María de la Sede de la ciudad de Sevilla, en Andalucía (España). La parte inferior de la torre corresponde al alminar de la antigua mezquita de la ciudad, de finales del siglo XII, levantado en época almohade, mientras que la parte superior es una construcción sobrepuesta en el siglo XVI, en época cristiana, para albergar el cuerpo de campanas. En su cúspide se halla una estatua de bronce que representa el Triunfo de la Fe y que tiene función de veleta, el Giraldillo. Mide 94,69 m metros de altura.")
                .urlImagen("https://sevillasecreta.co/wp-content/uploads/2020/08/shutterstock_156180494-1.jpg")
                .build());

        addMonumento(Monumento.builder()
                .id(2L)
                .codPais("MX")
                .nombrePais("México")
                .nombreCiudad("Ciudad de México")
                .latitud("19.42847")
                .longitud("-99.12766")
                .nombreMon("El Ángel de la Independencia")
                .descripcion("El Ángel de la Independencia es un monumento conmemorativo ubicado en la Ciudad de México, sobre Paseo de la Reforma. Construido en 1910 para celebrar el centenario de la Independencia de México, es una de las estructuras más icónicas de la ciudad.")
                .urlImagen("https://upload.wikimedia.org/wikipedia/commons/4/49/Angel_de_la_Independencia_Mexico_City.jpg")
                .build());

        addMonumento(Monumento.builder()
                .id(3L)
                .codPais("NO")
                .nombrePais("Noruega")
                .nombreCiudad("Oslo")
                .latitud("59.9139")
                .longitud("10.7522")
                .nombreMon("Vigeland Park")
                .descripcion("El Parque de Vigeland, situado en Oslo, Noruega, es el parque de esculturas más grande del mundo creado por un solo artista. Gustav Vigeland diseñó el parque, que contiene más de 200 esculturas de bronce, granito y hierro forjado.")
                .urlImagen("https://upload.wikimedia.org/wikipedia/commons/4/4f/Monolitten%2C_Vigelandsparken_-_2016-06-25.jpg")
                .build());

        addMonumento(Monumento.builder()
                .id(4L)
                .codPais("IT")
                .nombrePais("Italia")
                .nombreCiudad("Florencia")
                .latitud("43.7696")
                .longitud("11.2558")
                .nombreMon("Catedral de Santa María del Fiore")
                .descripcion("La Catedral de Santa María del Fiore, conocida popularmente como el Duomo de Florencia, es uno de los iconos más destacados de Italia. Construida entre los siglos XIII y XV, es famosa por su impresionante cúpula diseñada por Filippo Brunelleschi.")
                .urlImagen("https://upload.wikimedia.org/wikipedia/commons/a/a3/Florence_Duomo_from_Michelangelo_hill.jpg")
                .build());

        addMonumento(Monumento.builder()
                .id(5L)
                .codPais("IT")
                .nombrePais("Italia")
                .nombreCiudad("Florencia")
                .latitud("43.7686")
                .longitud("11.2559")
                .nombreMon("Ponte Vecchio")
                .descripcion("El Ponte Vecchio es un puente medieval de arco segmentado en Florencia, Italia, conocido por sus tiendas construidas a lo largo del puente, que originalmente eran ocupadas por carniceros, pero ahora están llenas de joyerías y souvenirs.")
                .urlImagen("https://upload.wikimedia.org/wikipedia/commons/8/8c/Ponte_Vecchio_%28Florence%29_-_April_2008.jpg")
                .build());

        addMonumento(Monumento.builder()
                .id(6L)
                .codPais("ES")
                .nombrePais("España")
                .nombreCiudad("Úbeda")
                .latitud("38.0136")
                .longitud("-3.3688")
                .nombreMon("Sacra Capilla del Salvador")
                .descripcion("La Sacra Capilla del Salvador es uno de los monumentos renacentistas más destacados de Úbeda, declarada Patrimonio de la Humanidad por la UNESCO. Fue construida en el siglo XVI como capilla funeraria y es conocida por su rica ornamentación.")
                .urlImagen("https://upload.wikimedia.org/wikipedia/commons/2/2c/Capilla_El_Salvador%2C_Ubeda.jpg")
                .build());

    }

    public Monumento addMonumento(Monumento monumento){
        listaMonumentos.put(monumento.getId(),monumento);
        return monumento;
    }

    public Optional<Monumento> getMonumento(Long id) {
        return Optional.ofNullable(listaMonumentos.get(id));
    }

    public List<Monumento> getAll() {
        return List.copyOf(listaMonumentos.values());
    }

    public Optional<Monumento> edit(Long id, Monumento nuevoMonumento) {
        return Optional.ofNullable(listaMonumentos.computeIfPresent(id, (k, m) -> {
            m.setCodPais(nuevoMonumento.getCodPais());
            m.setNombrePais(nuevoMonumento.getNombrePais());
            m.setNombreCiudad(nuevoMonumento.getNombreCiudad());
            m.setLatitud(nuevoMonumento.getLatitud());
            m.setLongitud(nuevoMonumento.getLongitud());
            m.setNombreMon(nuevoMonumento.getNombreMon());
            m.setDescripcion(nuevoMonumento.getDescripcion());
            m.setUrlImagen(nuevoMonumento.getUrlImagen());

            return m;
        }));
    }

    public void delete(Long id) {
        listaMonumentos.remove(id);
    }
}
