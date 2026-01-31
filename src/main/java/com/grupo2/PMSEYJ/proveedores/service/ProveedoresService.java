package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.proveedores.dto.GestionProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.dto.NuevaFacturaCompraDTO;
import com.grupo2.PMSEYJ.proveedores.dto.NuevoCotejoDTO;
import com.grupo2.PMSEYJ.proveedores.dto.NuevoProveedorDTO;

public interface ProveedoresService {

    boolean verificarExistenciaProveedor(String nombre);
    void insertarProveedor(NuevoProveedorDTO nuevoProveedor);
    GestionProveedorDTO consultarProveedorPorNombre(String nombre);
    void actualizarCorreoPorNombre(String correo, String nombre);
    void actualizarTelefonoPorNombre(String telefono, String nombre);
    void darDeAltaProveedor(String nombre);
    void darDeBajaProveedor(String nombre);
    void crearFacturaCompra(NuevaFacturaCompraDTO nuevaFacturaCompra);
    boolean verificarEstadoFacturaCompra(String num_fc);
    boolean verificarExistenciaFacturaCompra(String num_fc);
    void agregarProducto(NuevaFacturaCompraDTO nuevaFacturaCompra);

}
