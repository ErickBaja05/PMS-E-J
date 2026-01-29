package com.grupo2.PMSEYJ.inventarioYProductos.dto;

public class NuevoProductoDTO {
    private String codigo_barras;
    private String codigo_aux;
    private String nombre_p;
    private String descripcion;
    private String categoria;
    private String forma_venta;
    private String tipo_venta;
    private Double pvp;
    private String indice_t;

    public NuevoProductoDTO() {

    }


    public NuevoProductoDTO(String codigo_barras, String codigo_aux, String nombre_p, String descripcion, String categoria, String forma_venta, String tipo_venta, Double pvp, String indice_t) {
        this.codigo_barras = codigo_barras;
        this.codigo_aux = codigo_aux;
        this.nombre_p = nombre_p;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.forma_venta = forma_venta;
        this.tipo_venta = tipo_venta;
        this.pvp = pvp;
        this.indice_t = indice_t;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getCodigo_aux() {
        return codigo_aux;
    }

    public void setCodigo_aux(String codigo_aux) {
        this.codigo_aux = codigo_aux;
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
}
