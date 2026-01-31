package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.proveedores.dto.*;


import java.util.List;

public interface ProveedoresService {

    boolean verificarExistenciaProveedor(String nombre);
    void insertarProveedor(NuevoProveedorDTO nuevoProveedor);
    GestionProveedorDTO consultarProveedorPorNombre(String nombre);
    void actualizarCorreoPorNombre(String correo, String nombre);
    void actualizarTelefonoPorNombre(String telefono, String nombre);
    void darDeAltaProveedor(String nombre);
    void darDeBajaProveedor(String nombre);
    Integer crearFacturaCompra(NuevaFacturaCompraDTO nuevaFacturaCompra);
    boolean verificarEstadoFacturaCompra(String num_fc);
    boolean verificarExistenciaFacturaCompra(String num_fc);
    void agregarProducto(Integer id_fc , NuevoLoteDTO nuevoLote);
    boolean verificarSiYaFueIngresada(String num_fc);
    void ingresarFacturaCompra(String num_fc);
    List<CotejoDTO> consultarProductosFacturaPendiente(String num_fc);
    List<ProductoLoteDTO> armarTablaPendiente(List <CotejoDTO> pendientes);
    FacturaCompraPendienteDTO consultarFacturaCompra(String num_fc);


}
