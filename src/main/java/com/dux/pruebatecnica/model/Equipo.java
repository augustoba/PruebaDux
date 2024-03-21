package com.dux.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="EQUIPO")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 4, max = 50, message = "Debe ingresar un mínimo de 4 caracteres y máximo 50 para el campo EQUIPO")
    @Column(name="NOMBRE")
    private String nombre;
    @ManyToOne
    private Liga liga;
    @ManyToOne
    private Pais pais;

    public Equipo() {
    }

    public Equipo(Integer id, String nombre, Liga liga, Pais pais) {
        this.id = id;
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
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

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", liga=" + liga +
                ", pais=" + pais +
                '}';
    }
}
