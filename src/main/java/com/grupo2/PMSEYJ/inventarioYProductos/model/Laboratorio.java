package com.grupo2.PMSEYJ.inventarioYProductos.model;

public class Laboratorio {
        private Integer id_lab;
        private String nombre_lab;

        public Laboratorio(){

        }

        public Laboratorio(Integer id_lab, String nombre_lab) {
            this.id_lab = id_lab;
            this.nombre_lab = nombre_lab;
        }

    public Integer getId_lab() {
        return id_lab;
    }

    public void setId_lab(Integer id_lab) {
        this.id_lab = id_lab;
    }

    public String getNombre_lab() {
        return nombre_lab;
    }

    public void setNombre_lab(String nombre_lab) {
        this.nombre_lab = nombre_lab;
    }
}
