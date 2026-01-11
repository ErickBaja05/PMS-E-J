package com.grupo2.PMSEYJ.proveedores.model;

import javafx.beans.property.*;

public class ResultadoItem {

    private final StringProperty nombre;
    private final IntegerProperty cantFacturada; // Esperado
    private final IntegerProperty cantRecibida;  // Físico
    private final IntegerProperty diferencia;
    private final StringProperty estado;

    public ResultadoItem(String nombre, int cantFacturada, int cantRecibida) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cantFacturada = new SimpleIntegerProperty(cantFacturada);
        this.cantRecibida = new SimpleIntegerProperty(cantRecibida);

        // Cálculo automático de diferencia
        int diff = cantRecibida - cantFacturada;
        this.diferencia = new SimpleIntegerProperty(diff);

        // Cálculo automático de estado
        if (diff == 0) {
            this.estado = new SimpleStringProperty("CORRECTO");
        } else if (diff > 0) {
            this.estado = new SimpleStringProperty("EXCEDENTE");
        } else {
            this.estado = new SimpleStringProperty("FALTANTE");
        }
    }

    // Getters de Properties (Lambda ready)
    public StringProperty nombreProperty() { return nombre; }
    public IntegerProperty cantFacturadaProperty() { return cantFacturada; }
    public IntegerProperty cantRecibidaProperty() { return cantRecibida; }
    public IntegerProperty diferenciaProperty() { return diferencia; }
    public StringProperty estadoProperty() { return estado; }
}