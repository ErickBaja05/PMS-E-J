package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.proveedores.dto.ProveedorDTO;

public interface ProveedoresService {

    boolean verificarExistenciaProveedor(String nombre);
    void insertarProveedor(ProveedorDTO nuevoProveedor);
    ProveedorDTO consultarProveedorPorNombre(String nombre);
    void actualizarCorreoPorNombre(String correo, String nombre);
    void actualizarTelefonoPorNombre(String telefono, String nombre);
    void eliminarProveedorPorNombre(String nombre);

}
