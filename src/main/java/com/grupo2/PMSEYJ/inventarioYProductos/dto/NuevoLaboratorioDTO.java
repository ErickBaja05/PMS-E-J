package com.grupo2.PMSEYJ.inventarioYProductos.dto;

public class NuevoLaboratorioDTO {
    private String nombre_lab;

    public NuevoLaboratorioDTO() {

    }
    public NuevoLaboratorioDTO(String nombre_lab) {
        this.nombre_lab = nombre_lab;
    }

    public String getNombre_lab() {
        return nombre_lab;
    }

    public void setNombre_lab(String nombre_lab) {
        this.nombre_lab = nombre_lab;
    }
}
