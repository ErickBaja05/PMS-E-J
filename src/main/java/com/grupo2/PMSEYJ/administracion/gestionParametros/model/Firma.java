package com.grupo2.PMSEYJ.administracion.gestionParametros.model;

public class Firma {
    Integer id_firma;
    String ruta_fr;

    public Firma(){

    }

    public Firma(Integer id_firma, String ruta_fr) {
        this.id_firma = id_firma;
        this.ruta_fr = ruta_fr;
    }

    public Integer getId_firma() {
        return id_firma;
    }

    public void setId_firma(Integer id_firma) {
        this.id_firma = id_firma;
    }

    public String getRuta_fr() {
        return ruta_fr;
    }

    public void setRuta_fr(String ruta_fr) {
        this.ruta_fr = ruta_fr;
    }
}
