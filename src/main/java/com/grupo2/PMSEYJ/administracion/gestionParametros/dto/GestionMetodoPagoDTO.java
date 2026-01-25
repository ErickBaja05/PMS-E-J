package com.grupo2.PMSEYJ.administracion.gestionParametros.dto;

public class GestionMetodoPagoDTO {
    private String nombre_pago;
    private String estado_pago;

    public GestionMetodoPagoDTO() {}
    public GestionMetodoPagoDTO(String nombre_pago, String estado_pago) {
        this.nombre_pago = nombre_pago;
        this.estado_pago = estado_pago;
    }

    public String getNombre_pago() {
        return nombre_pago;
    }

    public void setNombre_pago(String nombre_pago) {
        this.nombre_pago = nombre_pago;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }
}
