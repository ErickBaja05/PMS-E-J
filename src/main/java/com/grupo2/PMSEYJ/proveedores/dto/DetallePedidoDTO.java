package com.grupo2.PMSEYJ.proveedores.dto;



public class DetallePedidoDTO {
    private Integer id_pedido;
    private String codigo_barras;
    private Integer cantidad;

    public DetallePedidoDTO(){

    }
    public DetallePedidoDTO(Integer id_pedido, String codigo_barras, Integer cantidad) {
        this.id_pedido = id_pedido;
        this.codigo_barras = codigo_barras;
        this.cantidad = cantidad;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
