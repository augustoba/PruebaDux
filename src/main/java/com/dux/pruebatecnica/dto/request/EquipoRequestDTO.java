package com.dux.pruebatecnica.dto.request;

import jakarta.validation.constraints.Size;

public class EquipoRequestDTO {
    @Size(min = 4, max = 50, message = "Debe ingresar un mínimo de 4 caracteres y máximo 50 para el campo EQUIPO")
    private String nombre;
    @Size(min = 4, max = 20, message = "Debe ingresar un mínimo de 4 caracteres y máximo 20 para el campo PAIS")
    private String pais;
    @Size(min = 4, max = 50, message = "Debe ingresar un mínimo de 4 caracteres y máximo 50 para el campo LIGA")
    private String liga;

    public EquipoRequestDTO() {
    }

    public EquipoRequestDTO(String equipo, String pais, String liga) {
        this.nombre = equipo;
        this.pais = pais;
        this.liga = liga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "EquipoRequestDTO{" +
                "equipo='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", liga='" + liga + '\'' +
                '}';
    }
}
