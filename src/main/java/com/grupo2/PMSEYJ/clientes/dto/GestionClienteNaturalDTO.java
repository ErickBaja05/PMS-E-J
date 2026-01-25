package com.grupo2.PMSEYJ.clientes.dto;

import java.time.LocalDate;

public class GestionClienteNaturalDTO {
    private String cedula;
    private String nombre;
    private String correo;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private String estado;

    public GestionClienteNaturalDTO() {

    }

    public GestionClienteNaturalDTO(String cedula, String nombre, String correo, String telefono, LocalDate fecha_nacimiento, String direccion, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
