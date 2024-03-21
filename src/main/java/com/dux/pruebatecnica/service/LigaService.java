package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Liga;
import com.dux.pruebatecnica.repository.LigaRepository;

public interface LigaService {

    Liga crearLiga(Liga liga);

    Liga buscarLigaPorNombre(String nombre);
}
