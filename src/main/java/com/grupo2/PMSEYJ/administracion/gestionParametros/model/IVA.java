package com.grupo2.PMSEYJ.administracion.gestionParametros.model;

public class IVA {
    public Double valorActual;
    public Double valorNuevo;


    public IVA(Double valorActual){
        this.valorActual=valorActual;

    }
    public IVA(Double valorActual, Double valorNuevo) {
        this.valorActual = valorActual;
        this.valorNuevo = valorNuevo;
    }

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(Double valorNuevo) {
        this.valorNuevo = valorNuevo;
    }
}
