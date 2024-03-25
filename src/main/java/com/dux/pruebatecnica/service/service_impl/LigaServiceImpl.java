package com.dux.pruebatecnica.service.service_impl;

import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.repository.LigaRepository;
import com.dux.pruebatecnica.service.LigaService;
import org.springframework.stereotype.Service;


@Service
public class LigaServiceImpl implements LigaService {

    public final LigaRepository ligaRepository;

    public LigaServiceImpl(LigaRepository ligaRepository) {

        this.ligaRepository = ligaRepository;
    }
    @Override
    public Liga buscarLigaPorNombre(String nombre){

        return ligaRepository.findByNombre(nombre);
    }

    @Override
    public Liga buscaryCrearLiga(String nombre){

        Liga ligaOpt = buscarLigaPorNombre(nombre);

        if (ligaOpt == null){

            Liga liga = new Liga();
            liga.setNombre(nombre);
            return ligaRepository.save(liga);

        }else  return ligaOpt;
    }
}
