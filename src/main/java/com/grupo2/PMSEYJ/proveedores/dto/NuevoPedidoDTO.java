package com.grupo2.PMSEYJ.proveedores.dto;

import java.time.LocalDate;

public class NuevoPedidoDTO {

    Integer id_prove;
    Integer id_pedido;

    public NuevoPedidoDTO() {

    }

    public NuevoPedidoDTO(Integer id_prove, Integer id_pedido) {
        this.id_prove = id_prove;
        this.id_pedido = id_pedido;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }
}
