package edu.trianasalesianos.edu.ejercicio03ClaseBici.service;

import edu.trianasalesianos.edu.ejercicio03ClaseBici.model.Bicicleta;
import edu.trianasalesianos.edu.ejercicio03ClaseBici.repository.RepositorioBicicleta;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioBicicleta {

    private RepositorioBicicleta repoBici;

    public List<Bicicleta> findAll(){
        List<Bicicleta> listaBicis =repoBici.findAll();
        if(listaBicis.isEmpty()){
            throw new EntityNotFoundException("No se han enctrado bicicletas");
        }else
            return listaBicis;
    }

    public Bicicleta findById(Long id){
        return repoBici.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe bicicleta con ese id"));
    }

    public Bicicleta updateBici(Long id, Bicicleta bici){
        return repoBici.findById(id).map(oldBici -> {
            oldBici.setModelo(bici.getModelo());
            oldBici.setMarca(bici.getMarca());
            oldBici.setEstacion(bici.getEstacion());
            oldBici.setEstado(bici.isEstado());

            return repoBici.save(bici);


        }).orElseThrow(()-> new EntityNotFoundException("Error al actualizar la bici"));
    }

    public void deleteBici(Long id){
        repoBici.deleteById(id);
    }

    public Bicicleta create(Bicicleta bici){
        return repoBici.save(bici);
    }
}