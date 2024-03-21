package com.dux.pruebatecnica.repository;

import com.dux.pruebatecnica.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga,Integer> {
    Liga findByNombre(String nombre);
}
