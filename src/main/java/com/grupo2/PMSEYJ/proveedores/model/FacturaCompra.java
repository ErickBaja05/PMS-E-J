package com.grupo2.PMSEYJ.proveedores.model;

import java.time.LocalDate;

public class FacturaCompra {
    Integer id_fc;
    String num_fc;
    String estado;
    LocalDate fecha_fc;
    Boolean fue_ingresada;
    Integer id_prove;

    public FacturaCompra(){

    }

    public Integer getId_fc() {
        return id_fc;
    }

    public void setId_fc(Integer id_fc) {
        this.id_fc = id_fc;
    }

    public String getNum_fc() {
        return num_fc;
    }

    public void setNum_fc(String num_fc) {
        this.num_fc = num_fc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_fc() {
        return fecha_fc;
    }

    public void setFecha_fc(LocalDate fecha_fc) {
        this.fecha_fc = fecha_fc;
    }

    public Boolean getFue_ingresada() {
        return fue_ingresada;
    }

    public void setFue_ingresada(Boolean fue_ingresada) {
        this.fue_ingresada = fue_ingresada;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }
}
