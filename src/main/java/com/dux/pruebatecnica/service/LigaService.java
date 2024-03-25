package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Liga;


public interface LigaService {

    Liga buscarLigaPorNombre(String nombre);

    Liga buscaryCrearLiga(String nombre);
}
