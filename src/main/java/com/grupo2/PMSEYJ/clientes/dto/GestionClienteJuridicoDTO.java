package com.grupo2.PMSEYJ.clientes.dto;

import java.time.LocalDate;

public class GestionClienteJuridicoDTO {
    private String ruc;
    private String razonSocial;
    private String correo;
    private String telefono;
    private String direccion;
    private String estado;


    public GestionClienteJuridicoDTO(String ruc, String razonSocial, String correo, String telefono, String direccion, String estado) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
