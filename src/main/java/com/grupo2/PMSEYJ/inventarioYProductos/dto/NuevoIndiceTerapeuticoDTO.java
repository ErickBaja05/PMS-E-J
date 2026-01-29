package com.grupo2.PMSEYJ.inventarioYProductos.dto;

public class NuevoIndiceTerapeuticoDTO {

    private String nombre_indice;

    public NuevoIndiceTerapeuticoDTO(String nombre_indice) {
        this.nombre_indice = nombre_indice;
    }

    public String getNombre_indice() {
        return nombre_indice;
    }

    public void setNombre_indice(String nombre_indice) {
        this.nombre_indice = nombre_indice;
    }
}
