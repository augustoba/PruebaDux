package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.dto.request.EquipoRequestDTO;
import com.dux.pruebatecnica.dto.response.EquipoResponseDTO;
import com.dux.pruebatecnica.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    Optional<Equipo> findEquipoById(Integer equipoId);


    EquipoResponseDTO crearEquipo(EquipoRequestDTO equipoRequestDTO);

    List<EquipoResponseDTO> listarEquipos();

    Equipo encontrarPorNombre(String nombre);

    List<Equipo> equiposListaNombres(String nombre);

    void eliminarEquipo(Integer equipoId);
}
