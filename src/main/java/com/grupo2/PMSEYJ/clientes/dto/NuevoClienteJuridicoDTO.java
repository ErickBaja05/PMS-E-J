package com.grupo2.PMSEYJ.clientes.dto;

public class NuevoClienteJuridicoDTO {
    private String ruc;
    private String razon_social;
    private String direccion_cj;
    private String correo_cj;
    private String telefono_cj;


    public NuevoClienteJuridicoDTO(String ruc, String razon_social, String direccion_cj, String correo_cj, String telefono_cj) {
        this.ruc = ruc;
        this.razon_social = razon_social;
        this.direccion_cj = direccion_cj;
        this.correo_cj = correo_cj;
        this.telefono_cj = telefono_cj;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion_cj() {
        return direccion_cj;
    }

    public void setDireccion_cj(String direccion_cj) {
        this.direccion_cj = direccion_cj;
    }

    public String getCorreo_cj() {
        return correo_cj;
    }

    public void setCorreo_cj(String correo_cj) {
        this.correo_cj = correo_cj;
    }

    public String getTelefono_cj() {
        return telefono_cj;
    }

    public void setTelefono_cj(String telefono_cj) {
        this.telefono_cj = telefono_cj;
    }
}
