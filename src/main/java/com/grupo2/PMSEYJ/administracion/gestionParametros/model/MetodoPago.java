package com.grupo2.PMSEYJ.administracion.gestionParametros.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MetodoPago {
    private Integer id_pago;
    private String nombre_pago;
    private String estado_pago;

    public MetodoPago(){

    }
    public MetodoPago(Integer id_pago, String nombre_pago, String estado_pago) {
        this.id_pago = id_pago;
        this.nombre_pago = nombre_pago;
        this.estado_pago = estado_pago;
    }

    public Integer getId_pago() {
        return id_pago;
    }

    public void setId_pago(Integer id_pago) {
        this.id_pago = id_pago;
    }

    public String getNombre_pago() {
        return nombre_pago;
    }

    public void setNombre_pago(String nombre_pago) {
        this.nombre_pago = nombre_pago;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }
}
