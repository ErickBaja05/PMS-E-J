package com.grupo2.PMSEYJ.proveedores.model;

import javafx.beans.property.*;

public class DetalleReporte {

    private final StringProperty codigo = new SimpleStringProperty();
    private final IntegerProperty compradas = new SimpleIntegerProperty();
    private final IntegerProperty real = new SimpleIntegerProperty();
    private final IntegerProperty diferencia = new SimpleIntegerProperty();
    private final StringProperty estado = new SimpleStringProperty();

    public DetalleReporte(String c, int comp, int r, int d, String e) {
        codigo.set(c);
        compradas.set(comp);
        real.set(r);
        diferencia.set(d);
        estado.set(e);
    }

    // ===== PROPERTIES =====
    public StringProperty codigoProperty() { return codigo; }
    public IntegerProperty compradasProperty() { return compradas; }
    public IntegerProperty realProperty() { return real; }
    public IntegerProperty diferenciaProperty() { return diferencia; }
    public StringProperty estadoProperty() { return estado; }

    // ===== GETTERS (opcional pero recomendado) =====
    public String getCodigo() { return codigo.get(); }
    public int getCompradas() { return compradas.get(); }
    public int getReal() { return real.get(); }
    public int getDiferencia() { return diferencia.get(); }
    public String getEstado() { return estado.get(); }
}

