package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model;


import javafx.beans.property.*;

public class Producto {
    private final StringProperty codigo;
    private final StringProperty nombre;
    private final StringProperty tipo;
    private final DoubleProperty precio;
    private final IntegerProperty stock;

    public Producto(String codigo, String nombre, String tipo, double precio, int stock) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public StringProperty codigoProperty() { return codigo; }
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty tipoProperty() { return tipo; }
    public DoubleProperty precioProperty() { return precio; }
    public IntegerProperty stockProperty() { return stock; }

    public String getCodigo() { return codigo.get(); }
    public String getNombre() { return nombre.get(); }
    public String getTipo() { return tipo.get(); }
    public double getPrecio() { return precio.get(); }
    public int getStock() { return stock.get(); }
}
