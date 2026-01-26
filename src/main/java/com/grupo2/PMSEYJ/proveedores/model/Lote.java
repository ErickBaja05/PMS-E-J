package com.grupo2.PMSEYJ.proveedores.model;

import java.time.LocalDate;

public class Lote {
    Integer id_lote;
    String codigo_aux;
    String num_lote;
    Integer stock;
    LocalDate fecha_vn;
    Double precio_compra;
    Integer rentabilidad;

    Integer tamano_caja;
    String estado;

    public Lote(){}

    public Lote(Integer id_lote, String codigo_aux, String num_lote, Integer stock, LocalDate fecha_vn, Double precio_compra, Integer rentabilidad, Integer tamano_caja, String estado) {
        this.id_lote = id_lote;
        this.codigo_aux = codigo_aux;
        this.num_lote = num_lote;
        this.stock = stock;
        this.fecha_vn = fecha_vn;
        this.precio_compra = precio_compra;
        this.rentabilidad = rentabilidad;
        this.tamano_caja = tamano_caja;
        this.estado = estado;
    }

    public Integer getId_lote() {
        return id_lote;
    }

    public void setId_lote(Integer id_lote) {
        this.id_lote = id_lote;
    }

    public String getCodigo_aux() {
        return codigo_aux;
    }

    public void setCodigo_aux(String codigo_aux) {
        this.codigo_aux = codigo_aux;
    }

    public String getNum_lote() {
        return num_lote;
    }

    public void setNum_lote(String num_lote) {
        this.num_lote = num_lote;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDate getFecha_vn() {
        return fecha_vn;
    }

    public void setFecha_vn(LocalDate fecha_vn) {
        this.fecha_vn = fecha_vn;
    }

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Integer getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(Integer rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public Integer getTamano_caja() {
        return tamano_caja;
    }

    public void setTamano_caja(Integer tamano_caja) {
        this.tamano_caja = tamano_caja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
