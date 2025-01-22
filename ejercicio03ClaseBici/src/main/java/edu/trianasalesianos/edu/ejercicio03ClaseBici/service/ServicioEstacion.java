package edu.trianasalesianos.edu.ejercicio03ClaseBici.service;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Estacion;
import edu.trianasalesianos.edu.ejercicio03ClaseBici.repository.RepositorioEstacion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioEstacion {

    private RepositorioEstacion repoEstacion;

    public Estacion create(Estacion estacion){
        return repoEstacion.save(estacion);
    }

    public Estacion findById(Long id){
        return repoEstacion.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe estación con ese id"));
    }

    public Estacion updateEstacion(Estacion estacion){
        return repoEstacion.findById(estacion.getId()).map(oldEstacion -> {
            oldEstacion.setCapacidad(estacion.getCapacidad());
            oldEstacion.setNombre(estacion.getNombre());
            oldEstacion.setCoordenadas(estacion.getCoordenadas());

            return repoEstacion.save(estacion);


        }).orElseThrow(()-> new EntityNotFoundException("Error al actualizar la estación"));
    }

    public void deleteBici(Long id){
        repoEstacion.deleteById(id);
    }
}
