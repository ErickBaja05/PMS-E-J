package com.grupo2.PMSEYJ.proveedores.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.time.LocalDate;


// ESTE OBJETO ES SOLO PARA MOSTRARLO EN LA TABLA
public class ProductoLoteDTO {

    private String codigo_barras;
    private String nombre_pro;
    private String num_lote;
    private String fecha_vn;
    private Integer cajas_compradas;
    private Double costo_unitario;   // costo por caja
    private Integer tamano_caja;      // unidades por caja
    private Integer rentabilidad;     // porcentaje
    private Integer cantidad;         // cantidad a mostrar en tabla
    private Boolean seVendePorCaja;
    private Boolean llevaIVA;         // checkbox: true si aplica IVA
    private Double iva;              // valor del IVA en porcentaje (ej. 15)

    // Constructor
    public ProductoLoteDTO(String codigo_barras, String nombre_pro, String num_lote,
                           String fecha_vn, Integer cajas_compradas,
                           Double costo_unitario, Integer tamano_caja,
                           Integer rentabilidad, Integer cantidad,
                           Boolean seVendePorCaja, Boolean llevaIVA, Double iva) {
        this.codigo_barras = codigo_barras;
        this.nombre_pro = nombre_pro;
        this.num_lote = num_lote;
        this.fecha_vn = fecha_vn;
        this.cajas_compradas = cajas_compradas;
        this.costo_unitario = costo_unitario;
        this.tamano_caja = tamano_caja;
        this.rentabilidad = rentabilidad;
        this.cantidad = cantidad;
        this.seVendePorCaja = seVendePorCaja;
        this.llevaIVA = llevaIVA;
        this.iva = iva;
    }


    public ProductoLoteDTO(String codigo_barras, String nombre_pro, String num_lote, String fecha_vn, Double costo_unitario, Boolean seVendePorCaja, Boolean llevaIVA) {
        this.codigo_barras = codigo_barras;
        this.nombre_pro = nombre_pro;
        this.num_lote = num_lote;
        this.fecha_vn = fecha_vn;
        this.costo_unitario = costo_unitario;
        this.seVendePorCaja = seVendePorCaja;
        this.llevaIVA = llevaIVA;
    }

    public ProductoLoteDTO(String codigo_barras, String nombre_pro, String num_lote, String fecha_vn, Double costo_unitario, Integer tamano_caja, Integer rentabilidad, Boolean seVendePorCaja, Boolean llevaIVA, Double iva) {
        this.codigo_barras = codigo_barras;
        this.nombre_pro = nombre_pro;
        this.num_lote = num_lote;
        this.fecha_vn = fecha_vn;
        this.costo_unitario = costo_unitario;
        this.tamano_caja = tamano_caja;
        this.rentabilidad = rentabilidad;
        this.seVendePorCaja = seVendePorCaja;
        this.llevaIVA = llevaIVA;
        this.iva = iva;
    }

    // --- Getters ---
    public String getCodigo_barras() { return codigo_barras; }
    public String getNombre_pro() { return nombre_pro; }
    public String getNum_lote() { return num_lote; }
    public String getFecha_vn() { return fecha_vn; }
    public Integer getCajas_compradas() { return cajas_compradas; }
    public Double getCosto_unitario() {
        if(seVendePorCaja){
            return costo_unitario;
        }else{
            return costo_unitario / tamano_caja;
        }

    }
    public Integer getTamano_caja() { return tamano_caja; }
    public Integer getRentabilidad() { return rentabilidad; }
    public Integer getCantidad() { return cantidad; }
    public Boolean getSeVendePorCaja() { return seVendePorCaja; }
    public Boolean getLlevaIVA() { return llevaIVA; }
    public Double getIva() { return iva; }


    // --- Método calculado ---
    public Double getPrecio_venta() {
        double base;

        if (seVendePorCaja != null && seVendePorCaja) {
            // Venta por caja
            base = costo_unitario * (1 + rentabilidad / 100.0);
        } else {
            // Venta por unidad
            double costoUnidad = (tamano_caja != null && tamano_caja > 0)
                    ? costo_unitario / (double) tamano_caja
                    : 0.0;
            base = costoUnidad * (1 + rentabilidad / 100.0);
        }

        // Aplicar IVA si corresponde
        if (llevaIVA != null && llevaIVA && iva != null && iva > 0) {
            base = base * (1 + iva / 100.0);
        }

        // Redondear a 2 decimales
        BigDecimal bd = BigDecimal.valueOf(base);
        bd = bd.setScale(2, RoundingMode.HALF_UP); // redondeo clásico
        return bd.doubleValue();
    }
}
