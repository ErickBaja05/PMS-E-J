package com.grupo2.PMSEYJ.inventarioYProductos.model;

import java.time.LocalDate;

public class Producto {
    private String codigo_aux;
    private Integer id_lab;
    private String codigo_br;
    private String nombre_p;
    private String descripcion;
    private String categoria;
    private String forma_venta;
    private String tipo_venta;
    private Double pvp;
    private String indice_t;
    private String principio_ac;
    private LocalDate fecha_uv;
    private Boolean tiene_iva;

    public Producto() {

    }

    public Producto(String codigo_aux, String codigo_br, String nombre_p, String descripcion, String categoria, String forma_venta, String tipo_venta, Double pvp, String indice_t) {
        this.codigo_aux = codigo_aux;
        this.codigo_br = codigo_br;
        this.nombre_p = nombre_p;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.forma_venta = forma_venta;
        this.tipo_venta = tipo_venta;
        this.pvp = pvp;
        this.indice_t = indice_t;
    }

    public String getCodigo_aux() {
        return codigo_aux;
    }

    public void setCodigo_aux(String codigo_aux) {
        this.codigo_aux = codigo_aux;
    }

    public Integer getId_lab() {
        return id_lab;
    }

    public void setId_lab(Integer id_lab) {
        this.id_lab = id_lab;
    }

    public String getCodigo_br() {
        return codigo_br;
    }

    public void setCodigo_br(String codigo_br) {
        this.codigo_br = codigo_br;
    }

    public String getNombre_p() {
        return nombre_p;
    }

    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getForma_venta() {
        return forma_venta;
    }

    public void setForma_venta(String forma_venta) {
        this.forma_venta = forma_venta;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public String getIndice_t() {
        return indice_t;
    }

    public void setIndice_t(String indice_t) {
        this.indice_t = indice_t;
    }

    public String getPrincipio_ac() {
        return principio_ac;
    }

    public void setPrincipio_ac(String principio_ac) {
        this.principio_ac = principio_ac;
    }

    public LocalDate getFecha_uv() {
        return fecha_uv;
    }

    public void setFecha_uv(LocalDate fecha_uv) {
        this.fecha_uv = fecha_uv;
    }

    public Boolean getTiene_iva() {
        return tiene_iva;
    }

    public void setTiene_iva(Boolean tiene_iva) {
        this.tiene_iva = tiene_iva;
    }
}
