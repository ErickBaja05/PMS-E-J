package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VentaArcsa {
    private final StringProperty registroSanitario;
    private final StringProperty producto;
    private final StringProperty PrecioVenta;
    private final StringProperty PVP;
    private final StringProperty numeroCajasVendidas;
    private final StringProperty numeroFraccionesVendidas;

    public VentaArcsa(String registroSanitario, String producto, String precioVenta, String pvp, String numeroCajasVendidas, String numeroFraccionesVendidas) {
        this.registroSanitario = new SimpleStringProperty(registroSanitario);
        this.producto = new SimpleStringProperty(producto);
        this.PrecioVenta = new SimpleStringProperty(precioVenta);
        this.PVP = new SimpleStringProperty(pvp);
        this.numeroCajasVendidas = new SimpleStringProperty(numeroCajasVendidas);
        this.numeroFraccionesVendidas = new  SimpleStringProperty(numeroFraccionesVendidas);
    }

    public String getRegistroSanitario() {
        return registroSanitario.get();
    }

    public StringProperty registroSanitarioProperty() {
        return registroSanitario;
    }

    public String getProducto() {
        return producto.get();
    }

    public StringProperty productoProperty() {
        return producto;
    }

    public String getPrecioVenta() {
        return PrecioVenta.get();
    }

    public StringProperty precioVentaProperty() {
        return PrecioVenta;
    }

    public String getPVP() {
        return PVP.get();
    }

    public StringProperty PVPProperty() {
        return PVP;
    }

    public String getNumeroCajasVendidas() {
        return numeroCajasVendidas.get();
    }

    public StringProperty numeroCajasVendidasProperty() {
        return numeroCajasVendidas;
    }

    public String getNumeroFraccionesVendidas() {
        return numeroFraccionesVendidas.get();
    }

    public StringProperty numeroFraccionesVendidasProperty() {
        return numeroFraccionesVendidas;
    }
}
