package com.grupo2.PMSEYJ.proveedores.dto;

public class CotejoDTO {
    Integer id_fc;
    Integer id_lote;
    Integer cantidad;

    public CotejoDTO(){

    }

    public CotejoDTO(Integer id_fc, Integer id_lote, Integer cantidad) {
        this.id_fc = id_fc;
        this.id_lote = id_lote;
        this.cantidad = cantidad;
    }

    public Integer getId_fc() {
        return id_fc;
    }

    public void setId_fc(Integer id_fc) {
        this.id_fc = id_fc;
    }

    public Integer getId_lote() {
        return id_lote;
    }

    public void setId_lote(Integer id_lote) {
        this.id_lote = id_lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }



}
