package com.grupo2.PMSEYJ.inventarioYProductos.service;

import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoIndiceTerapeuticoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoLaboratorioDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoProductoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.ProductoPedidoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;

public interface ProductosService {

    void insertarLaboratorio(NuevoLaboratorioDTO nuevoLaboratorio);
    boolean verificarExistenciaLaboratorio(String nombreLaboratorio);
    void insertarProducto(NuevoProductoDTO nuevoProducto, NuevoLaboratorioDTO nuevoLaboratorio, NuevoIndiceTerapeuticoDTO nuevoIndiceTerapeutico);
    boolean verificarExistenciaProductoBarras(String codBarras);
    boolean verificarExistenciaProductoAux(String codAux);
    void insertarIndiceTerapeutico(NuevoIndiceTerapeuticoDTO nuevoIndiceTerapeutico);
    boolean verificarExistenciaIndiceTerapeutico(String nombreIndiceTerapeutico);
    Producto consultarProductoPorCodigoBarras(String codigoBarras);
    ProductoPedidoDTO consultarProductoPorCodigoBarra(String codigoBarras);


}
