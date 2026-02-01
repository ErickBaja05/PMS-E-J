package com.grupo2.PMSEYJ.proveedores.dto;

import java.time.LocalDate;

public class NuevaFacturaCompraDTO {
    String num_fc;
    LocalDate fecha_fc;
    Integer id_prove;


    public NuevaFacturaCompraDTO() {

    }

    public NuevaFacturaCompraDTO(String num_fc, LocalDate fecha_fc, Integer id_prove) {
        this.num_fc = num_fc;
        this.fecha_fc = fecha_fc;
        this.id_prove = id_prove;
    }

    public String getNum_fc() {
        return num_fc;
    }

    public void setNum_fc(String num_fc) {
        this.num_fc = num_fc;
    }

    public LocalDate getFecha_fc() {
        return fecha_fc;
    }

    public void setFecha_fc(LocalDate fecha_fc) {
        this.fecha_fc = fecha_fc;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }
}
