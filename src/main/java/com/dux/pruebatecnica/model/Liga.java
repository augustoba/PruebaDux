package com.dux.pruebatecnica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LIGA")
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;

    public Liga() {
    }

    public Liga(Integer id, String nombre) {
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
        return "Liga{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
