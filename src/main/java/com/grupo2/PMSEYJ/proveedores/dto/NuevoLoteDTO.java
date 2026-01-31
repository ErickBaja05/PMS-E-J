package com.grupo2.PMSEYJ.proveedores.dto;

import java.time.LocalDate;

public class NuevoLoteDTO {
    String codigo_barras;
    String num_lote;
    Integer stock;
    Integer n_cajasCompradas;
    LocalDate fecha_vn;
    Double precio_compra;
    Integer rentabilidad;
    Integer tamano_caja;
    String estado;
    Boolean tiene_iva;

    public NuevoLoteDTO(){}

    public NuevoLoteDTO(String codigo_barras, String num_lote, Integer n_cajasCompradas, LocalDate fecha_vn, Double precio_compra, Integer rentabilidad, Integer tamano_caja, String estado, Boolean tiene_iva) {
        this.codigo_barras = codigo_barras;
        this.num_lote = num_lote;
        this.n_cajasCompradas = n_cajasCompradas;
        this.fecha_vn = fecha_vn;
        this.precio_compra = precio_compra;
        this.rentabilidad = rentabilidad;
        this.tamano_caja = tamano_caja;
        this.estado = estado;
        this.tiene_iva = tiene_iva;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
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

    public Integer getN_cajasCompradas() {
        return n_cajasCompradas;
    }

    public void setN_cajasCompradas(Integer n_cajasCompradas) {
        this.n_cajasCompradas = n_cajasCompradas;
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

    public Boolean getTiene_iva() {
        return tiene_iva;
    }

    public void setTiene_iva(Boolean tiene_iva) {
        this.tiene_iva = tiene_iva;
    }
}
