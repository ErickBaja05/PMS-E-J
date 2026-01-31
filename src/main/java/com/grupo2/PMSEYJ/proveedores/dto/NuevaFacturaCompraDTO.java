package com.grupo2.PMSEYJ.proveedores.dto;

import java.time.LocalDate;

public class NuevaFacturaCompraDTO {
    String num_fc;
    LocalDate fecha_fc;

    public NuevaFacturaCompraDTO(String num_fc, LocalDate fecha_fc) {
        this.num_fc = num_fc;
        this.fecha_fc = fecha_fc;
    }

    public NuevaFacturaCompraDTO() {

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


}
