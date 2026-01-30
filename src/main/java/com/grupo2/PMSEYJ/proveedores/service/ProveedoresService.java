package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.proveedores.dto.GestionProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.dto.NuevoProveedorDTO;

public interface ProveedoresService {

    boolean verificarExistenciaProveedor(String nombre);
    void insertarProveedor(NuevoProveedorDTO nuevoProveedor);
    GestionProveedorDTO consultarProveedorPorNombre(String nombre);
    void actualizarCorreoPorNombre(String correo, String nombre);
    void actualizarTelefonoPorNombre(String telefono, String nombre);
    void eliminarProveedorPorNombre(String nombre);
    void darDeAltaProveedor(String nombre);
    void darDeBajaProveedor(String nombre);

}
