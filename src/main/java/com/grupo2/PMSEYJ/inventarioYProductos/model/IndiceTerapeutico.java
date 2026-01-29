package com.grupo2.PMSEYJ.inventarioYProductos.model;

public class IndiceTerapeutico {
    private Integer id_indice_terapeutico;
    private String nombre_indice;


    public IndiceTerapeutico() {

    }

    public IndiceTerapeutico(Integer id_indice_terapeutico, String nombre_indice) {
        this.id_indice_terapeutico = id_indice_terapeutico;
        this.nombre_indice = nombre_indice;
    }

    public Integer getId_indice_terapeutico() {
        return id_indice_terapeutico;
    }

    public void setId_indice_terapeutico(Integer id_indice_terapeutico) {
        this.id_indice_terapeutico = id_indice_terapeutico;
    }

    public String getNombre_indice() {
        return nombre_indice;
    }

    public void setNombre_indice(String nombre_indice) {
        this.nombre_indice = nombre_indice;
    }
}
