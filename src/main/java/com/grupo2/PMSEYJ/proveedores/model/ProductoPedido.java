package com.grupo2.PMSEYJ.proveedores.model;

import javafx.beans.property.*;

public class ProductoPedido {

    private final IntegerProperty num = new SimpleIntegerProperty();
    private final StringProperty codigo = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final IntegerProperty cantidad = new SimpleIntegerProperty();

    public ProductoPedido(int num, String codigo, String nombre, int cantidad) {
        this.num.set(num);
        this.codigo.set(codigo);
        this.nombre.set(nombre);
        this.cantidad.set(cantidad);
    }

    // ===== GETTERS NORMALES =====
    public int getNum() { return num.get(); }
    public String getCodigo() { return codigo.get(); }
    public String getNombre() { return nombre.get(); }
    public int getCantidad() { return cantidad.get(); }

    // ===== SETTERS =====
    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    // ===== PROPERTIES (CLAVE PARA TABLEVIEW) =====
    public IntegerProperty numProperty() { return num; }
    public StringProperty codigoProperty() { return codigo; }
    public StringProperty nombreProperty() { return nombre; }
    public IntegerProperty cantidadProperty() { return cantidad; }
}
