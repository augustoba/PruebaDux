package com.dux.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="PAIS")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="NOMBRE")
    @Size(min = 4, max = 20, message = "Debe ingresar un mínimo de 4 caracteres y máximo 20 para el campo PAIS")
    private String nombre;

    public Pais() {
    }

    public Pais(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
