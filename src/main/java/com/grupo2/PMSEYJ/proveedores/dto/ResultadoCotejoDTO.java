package com.grupo2.PMSEYJ.proveedores.dto;

public class ResultadoCotejoDTO {
    String nombre_pro;
    Integer cajas_compradas;
    Integer cajas_recibidas;
    Integer diferencia;
    String resultado;

    public ResultadoCotejoDTO() {

    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public Integer getCajas_compradas() {
        return cajas_compradas;
    }

    public void setCajas_compradas(Integer cajas_compradas) {
        this.cajas_compradas = cajas_compradas;
    }

    public Integer getCajas_recibidas() {
        return cajas_recibidas;
    }

    public void setCajas_recibidas(Integer cajas_recibidas) {
        this.cajas_recibidas = cajas_recibidas;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
