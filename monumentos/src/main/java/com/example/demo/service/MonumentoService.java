package com.example.demo.service;

import com.example.demo.model.Monumento;
import com.example.demo.repository.MonumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonumentoService {


    private final MonumentoRepository monumentoRepository;

    public Monumento addMonumento(Monumento monumento){
        if(this.monumentoRepository.getMonumento(monumento.getId()).isPresent()){
            throw new IllegalArgumentException("El monumento con id: " + monumento.getId() + " ya existe");
        }
        return monumentoRepository.addMonumento(monumento);
    }

    public Monumento getMonumento(Long id){
       return monumentoRepository.getMonumento(id)
               .orElseThrow( ()-> new IllegalArgumentException("El monumento con id: " + id + " no se ha encontrado"));


    }
}
