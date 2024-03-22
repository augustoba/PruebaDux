package com.dux.pruebatecnica.repository;

import com.dux.pruebatecnica.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo,Integer> {
    Equipo findByNombre(String nombre);

    @Query("SELECT e FROM Equipo e WHERE LOWER(e.nombre) LIKE LOWER(concat('%', :nombre, '%')) OR LOWER(e.liga.nombre) LIKE LOWER(concat('%', :nombre, '%')) OR LOWER(e.pais.nombre) LIKE LOWER(concat('%', :nombre, '%'))")
    List<Equipo> findByNombreEquipoLigaPais(@Param("nombre") String nombre);
}
