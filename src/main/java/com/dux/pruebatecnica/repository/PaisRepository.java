package com.dux.pruebatecnica.repository;

import com.dux.pruebatecnica.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais,Integer> {
    Pais findByNombre(String nombre);
}
