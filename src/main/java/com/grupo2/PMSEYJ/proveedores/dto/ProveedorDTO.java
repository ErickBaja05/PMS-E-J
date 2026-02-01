package com.grupo2.PMSEYJ.proveedores.dto;

public class ProveedorDTO {
    Integer id_prove;
    String nombre_pro;

    public ProveedorDTO(){

    }

    public ProveedorDTO(Integer id_prove, String nombre_pro) {
        this.id_prove = id_prove;
        this.nombre_pro = nombre_pro;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }
}
