package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    Optional<Equipo> findEquipoById(Integer equipoId);

    Equipo crearEquipo(Equipo equipo);

    List<Equipo> listarEquipos();

    Equipo encontrarPorNombre(String nombre);
}
