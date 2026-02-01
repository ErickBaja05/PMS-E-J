package com.grupo2.PMSEYJ.proveedores.dto;

public class ResumenPedidoDTO {
    String nombre_pro;
    Integer cantidad;

    public ResumenPedidoDTO(String nombre_pro, Integer cantidad) {
        this.nombre_pro = nombre_pro;
        this.cantidad = cantidad;
    }

    public ResumenPedidoDTO() {

    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
