package com.grupo2.PMSEYJ.administracion.gestionParametros.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MetodoPago {

    private final StringProperty nombre;
    private final StringProperty estado;

    public MetodoPago(String nombre, String estado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}
