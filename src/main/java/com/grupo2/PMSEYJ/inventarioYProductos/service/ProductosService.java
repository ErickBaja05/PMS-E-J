package com.grupo2.PMSEYJ.inventarioYProductos.service;

import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoLaboratorioDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoProductoDTO;

public interface ProductosService {

    void insertarLaboratorio(NuevoLaboratorioDTO nuevoLaboratorio);
    boolean verificarExistenciaLaboratorio(String nombreLaboratorio);
    void insertarProducto(NuevoProductoDTO nuevoProducto, NuevoLaboratorioDTO nuevoLaboratorio);
    boolean verificarExistenciaProductoBarras(String codBarras);
    boolean verificarExistenciaProductoAux(String codAux);


}
