package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model;

import javafx.beans.property.*;

public class ItemCarrito {
    private final StringProperty producto;
    private final IntegerProperty cantidad;
    private final DoubleProperty subtotal;

    public ItemCarrito(String producto, int cantidad, double subtotal) {
        this.producto = new SimpleStringProperty(producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.subtotal = new SimpleDoubleProperty(subtotal);
    }

    public StringProperty productoProperty() { return producto; }
    public IntegerProperty cantidadProperty() { return cantidad; }
    public DoubleProperty subtotalProperty() { return subtotal; }
}