package com.dux.pruebatecnica.model;

import jakarta.persistence.*;

@Entity
@Table(name="EQUIPO")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
