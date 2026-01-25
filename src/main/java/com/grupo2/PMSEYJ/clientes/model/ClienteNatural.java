package com.grupo2.PMSEYJ.clientes.model;

import java.time.LocalDate;

public class ClienteNatural {
    private Integer id_cn;
    private String cedula_cn;
    private String nombre_cn;
    private String direccion_cn;
    private String correo_cn;
    private String telefono_cn;
    private LocalDate fecha_cn;
    private String estado_cn;

    public ClienteNatural() {

    }

    public ClienteNatural(Integer id_cn, String cedula_cn, String nombre_cn, String direccion_cn, String correo_cn, String telefono_cn, LocalDate fecha_cn, String estado_cn) {
        this.id_cn = id_cn;
        this.cedula_cn = cedula_cn;
        this.nombre_cn = nombre_cn;
        this.direccion_cn = direccion_cn;
        this.correo_cn = correo_cn;
        this.telefono_cn = telefono_cn;
        this.fecha_cn = fecha_cn;
        this.estado_cn = estado_cn;
    }

    public Integer getId_cn() {
        return id_cn;
    }

    public void setId_cn(Integer id_cn) {
        this.id_cn = id_cn;
    }

    public String getCedula_cn() {
        return cedula_cn;
    }

    public void setCedula_cn(String cedula_cn) {
        this.cedula_cn = cedula_cn;
    }

    public String getNombre_cn() {
        return nombre_cn;
    }

    public void setNombre_cn(String nombre_cn) {
        this.nombre_cn = nombre_cn;
    }

    public String getDireccion_cn() {
        return direccion_cn;
    }

    public void setDireccion_cn(String direccion_cn) {
        this.direccion_cn = direccion_cn;
    }

    public String getCorreo_cn() {
        return correo_cn;
    }

    public void setCorreo_cn(String correo_cn) {
        this.correo_cn = correo_cn;
    }

    public String getTelefono_cn() {
        return telefono_cn;
    }

    public void setTelefono_cn(String telefono_cn) {
        this.telefono_cn = telefono_cn;
    }


    public LocalDate getFecha_cn() {
        return fecha_cn;
    }

    public void setFecha_cn(LocalDate fecha_cn) {
        this.fecha_cn = fecha_cn;
    }

    public String getEstado_cn() {
        return estado_cn;
    }

    public void setEstado_cn(String estado_cn) {
        this.estado_cn = estado_cn;
    }
}
