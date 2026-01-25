package com.grupo2.PMSEYJ.ventasYFacturacion.caja.model;

import javafx.beans.property.*;

public class MovimientoCaja {

    private final StringProperty tipo;
    private final DoubleProperty monto;

    public MovimientoCaja(String tipo, double monto) {
        this.tipo = new SimpleStringProperty(tipo);
        this.monto = new SimpleDoubleProperty(monto);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public double getMonto() {
        return monto.get();
    }

    public void setMonto(double monto) {
        this.monto.set(monto);
    }

    public DoubleProperty montoProperty() {
        return monto;
    }
}
