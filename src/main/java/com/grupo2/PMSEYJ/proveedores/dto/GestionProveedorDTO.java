package com.grupo2.PMSEYJ.proveedores.dto;

public class GestionProveedorDTO {
    String nombre_pro;
    String telefono_pro;
    String correo_pro;
    String estado_pv;


    public GestionProveedorDTO() {

    }

    public GestionProveedorDTO(String nombre_pro, String telefono_pro, String correo_pro, String estado_pv) {
        this.nombre_pro = nombre_pro;
        this.telefono_pro = telefono_pro;
        this.correo_pro = correo_pro;
        this.estado_pv = estado_pv;
    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public String getTelefono_pro() {
        return telefono_pro;
    }

    public void setTelefono_pro(String telefono_pro) {
        this.telefono_pro = telefono_pro;
    }

    public String getCorreo_pro() {
        return correo_pro;
    }

    public void setCorreo_pro(String correo_pro) {
        this.correo_pro = correo_pro;
    }

    public String getEstado_pv() {
        return estado_pv;
    }

    public void setEstado_pv(String estado_pv) {
        this.estado_pv = estado_pv;
    }
}
