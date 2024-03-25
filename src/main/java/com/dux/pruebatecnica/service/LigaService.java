package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.repository.LigaRepository;

public interface LigaService {

  /*  Liga crearLiga(String nombre);*/

    Liga buscarLigaPorNombre(String nombre);

    Liga buscaryCrearLiga(String nombre);
}
