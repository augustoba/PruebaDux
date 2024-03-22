package com.dux.pruebatecnica.dto.response;

public class EquipoResponseDTO {

    private Integer id;
    private String equipo;
    private String pais;
    private String liga;

    public EquipoResponseDTO() {
    }

    public EquipoResponseDTO(Integer id, String equipo, String pais, String liga) {
        this.id = id;
        this.equipo = equipo;
        this.pais = pais;
        this.liga = liga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
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
        return "EquipoResponseDTO{" +
                "id=" + id +
                ", equipo='" + equipo + '\'' +
                ", pais='" + pais + '\'' +
                ", liga='" + liga + '\'' +
                '}';
    }
}
