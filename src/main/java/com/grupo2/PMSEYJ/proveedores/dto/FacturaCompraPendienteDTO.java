package com.grupo2.PMSEYJ.proveedores.dto;

public class FacturaCompraPendienteDTO {
    Integer id_fc;
    String num_fc;

    public FacturaCompraPendienteDTO() {

    }
    public FacturaCompraPendienteDTO(Integer id_fc, String num_fc) {
        this.id_fc = id_fc;
        this.num_fc = num_fc;
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
}
