package com.grupo2.PMSEYJ.inventarioYProductos.dto;

public class ProductoPedidoDTO {
    private String codigo_barras;
    private String nombre_p;
    private Integer numero_p;
    private Integer cantidad_cajas;

    public ProductoPedidoDTO() {

    }

    public ProductoPedidoDTO(String codigo_barras, String nombre_p) {
        this.codigo_barras = codigo_barras;
        this.nombre_p = nombre_p;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNombre_p() {
        return nombre_p;
    }

    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }

    public Integer getNumero_p() {
        return numero_p;
    }

    public void setNumero_p(Integer numero_p) {
        this.numero_p = numero_p;
    }

    public Integer getCantidad_cajas() {
        return cantidad_cajas;
    }

    public void setCantidad_cajas(Integer cantidad_cajas) {
        this.cantidad_cajas = cantidad_cajas;
    }
}
