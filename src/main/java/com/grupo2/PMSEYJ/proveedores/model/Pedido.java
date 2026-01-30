package com.grupo2.PMSEYJ.proveedores.model;

public class Pedido {
    Integer id_pedido;
    Integer id_prove;
    String estado;

    public Pedido(){

    }

    public Pedido(Integer id_pedido, Integer id_prove, String estado) {
        this.id_pedido = id_pedido;
        this.id_prove = id_prove;
        this.estado = estado;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
