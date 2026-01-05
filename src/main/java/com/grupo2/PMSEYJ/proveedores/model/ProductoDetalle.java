package com.grupo2.PMSEYJ.proveedores.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductoDetalle {

    private final StringProperty nombre;
    private final IntegerProperty cantidad;

    public ProductoDetalle(String nombre, int cantidad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    // 🔹 getters normales
    public String getNombre() {
        return nombre.get();
    }

    public int getCantidad() {
        return cantidad.get();
    }

    // 🔹 setters
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    // 🔹 properties (CRÍTICAS para TableView)
    public StringProperty nombreProperty() {
        return nombre;
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }
}
