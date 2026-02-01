package com.grupo2.PMSEYJ.proveedores.dto;

public class LotePedidoDTO {
    String codigo_barras;
    String num_lote;


    public LotePedidoDTO(String codigo_barras, String num_lote) {
        this.codigo_barras = codigo_barras;
        this.num_lote = num_lote;
    }

    public LotePedidoDTO() {

    }


    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNum_lote() {
        return num_lote;
    }

    public void setNum_lote(String num_lote) {
        this.num_lote = num_lote;
    }
}

