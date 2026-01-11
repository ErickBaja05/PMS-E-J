package com.grupo2.PMSEYJ.proveedores.model;

import javafx.beans.property.*;

public class DetalleFactura {

    private final StringProperty nombre;
    private final StringProperty lote;
    private final StringProperty vencimiento;
    private final DoubleProperty costo;
    private final IntegerProperty cajas;
    private final DoubleProperty precioVenta;

    public DetalleFactura(String nombre, String lote, String vencimiento,
                           double costo, int cajas, double precioVenta) {

        this.nombre = new SimpleStringProperty(nombre);
        this.lote = new SimpleStringProperty(lote);
        this.vencimiento = new SimpleStringProperty(vencimiento);
        this.costo = new SimpleDoubleProperty(costo);
        this.cajas = new SimpleIntegerProperty(cajas);
        this.precioVenta = new SimpleDoubleProperty(precioVenta);
    }

    // --- Getters normales ---
    public String getNombre() { return nombre.get(); }
    public String getLote() { return lote.get(); }
    public String getVencimiento() { return vencimiento.get(); }
    public double getCosto() { return costo.get(); }
    public int getCajas() { return cajas.get(); }
    public double getPrecioVenta() { return precioVenta.get(); }

    // --- Property getters (recomendado JavaFX) ---
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty loteProperty() { return lote; }
    public StringProperty vencimientoProperty() { return vencimiento; }
    public DoubleProperty costoProperty() { return costo; }
    public IntegerProperty cajasProperty() { return cajas; }
    public DoubleProperty precioVentaProperty() { return precioVenta; }
}
