package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model;

import javafx.beans.property.*;

public class Factura {
    private final StringProperty fechaEmision;
    private final StringProperty numeroFactura;
    private final StringProperty estado;
    private final StringProperty identificacionCliente;



    public Factura(String fechaEmision, String numeroFactura, String estado, String identificacionCliente) {


        this.fechaEmision = new SimpleStringProperty(fechaEmision);
        this.numeroFactura = new SimpleStringProperty(numeroFactura);
        this.estado = new SimpleStringProperty(estado);
        this.identificacionCliente = new SimpleStringProperty(identificacionCliente);

    }


    public String getFechaEmision() {
        return fechaEmision.get();
    }

    public StringProperty fechaEmisionProperty() {
        return fechaEmision;
    }

    public String getNumeroFactura() {
        return numeroFactura.get();
    }

    public StringProperty numeroFacturaProperty() {
        return numeroFactura;
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente.get();
    }

    public StringProperty identificacionClienteProperty() {
        return identificacionCliente;
    }
}
