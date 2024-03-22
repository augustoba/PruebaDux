package com.dux.pruebatecnica.repository;

import com.dux.pruebatecnica.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo,Integer> {
    Equipo findByNombre(String nombre);
}
