package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venta {
    private final StringProperty fecha;
    private final StringProperty valor;




    public Venta(String fecha, String valor) {


        this.fecha = new SimpleStringProperty(fecha);
        this.valor = new SimpleStringProperty(valor);

    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public String getValor() {
        return valor.get();
    }

    public StringProperty valorProperty() {
        return valor;
    }
}
