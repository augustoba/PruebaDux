package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Pais;

public interface PaisService {

    Pais buscarPaisPorNombre(String nombre);

    Pais buscaryCrearPais(String nombre);
}
