package com.dux.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="EQUIPO")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NOMBRE")
    @Size(min = 4, max = 50, message="Debe ingresar un minimo de 4 caracteres y maximo 50 para el campo NOMBRE")
    private String nombre;

    @Column(name="LIGA")
    @Size(min = 4, max = 50, message="Debe ingresar un minimo de 4 caracteres y maximo 50 para el campo LIGA")
    private String liga;

    @Column(name="PAIS")
    @Size(min = 4, max = 20, message="Debe ingresar un minimo de 4 caracteres y maximo 20 para el campo PAIS")
    private String pais;

    public Equipo() {
    }

    public Equipo(Integer id, String nombre, String liga, String pais) {
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

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
