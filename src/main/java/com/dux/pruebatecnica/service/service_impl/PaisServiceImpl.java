package com.dux.pruebatecnica.service.service_impl;

import com.dux.pruebatecnica.model.Pais;
import com.dux.pruebatecnica.repository.PaisRepository;
import com.dux.pruebatecnica.service.PaisService;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl implements PaisService {

    private final PaisRepository paisRepository;

    public PaisServiceImpl(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }


    @Override
    public Pais buscarPaisPorNombre(String nombre){
        return paisRepository.findByNombre(nombre);
    }

    @Override
    public Pais buscaryCrearPais(String nombre){

        Pais paisOpt = buscarPaisPorNombre(nombre);

        if (paisOpt == null){
            Pais pais = new Pais();
            pais.setNombre(nombre);

            return  paisRepository.save(pais);
        }else  return paisOpt;

    }
}
