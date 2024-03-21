package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Pais;

public interface PaisService {
    Pais crearPais(String nombre);

    Pais buscarPaisPorNombre(String nombre);
}
