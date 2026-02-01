package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.inventarioYProductos.dto.ProductoPedidoDTO;
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
    boolean verificarEstadoFacturaCompra(String num_fc, Integer id_prove);
    boolean verificarExistenciaFacturaCompra(String num_fc, Integer id_fc);
    void agregarProducto(Integer id_fc , NuevoLoteDTO nuevoLote);
    boolean verificarSiYaFueIngresada(String num_fc,Integer id_prove);
    void ingresarFacturaCompra(String num_fc, Integer id_prove);
    List<CotejoDTO> consultarProductosFacturaPendiente(String num_fc,Integer id_prove);
    List<ProductoLoteDTO> armarTablaPendiente(List <CotejoDTO> pendientes);
    FacturaCompraPendienteDTO consultarFacturaCompra(String num_fc, Integer id_prove);
    List<ProveedorDTO> consultarTodosLosProveedores();
    ProveedorDTO consultarProveedorNombre(String nombre);
    Integer insertarPedido();
    void insertarDetallePedido(DetallePedidoDTO nuevoDetallePedido);
    DetallePedidoDTO consultarDetallePedido(Integer id_pedido, String codigo_barras);
    void actualizarDetallePedido(Integer cantidad,Integer id_pedido, String codigo_barras);
    void eliminarDetallePedido(Integer id_pedido, String codigo_barras);
    List<ProductoPedidoDTO> cargarPedidoPendiente(Integer id_pedido);
    NuevoPedidoDTO consultarPedidoPendiente ();
    void definirProveedorAPedido(Integer id_pedido, Integer id_proveedor);
    void enviarPedido(Integer id_pedido, String estado);
    GestionProveedorDTO consultarProveedorPorID(Integer id_prove);
    LotePedidoDTO consultarLotePorId(Integer id_lote);


}
