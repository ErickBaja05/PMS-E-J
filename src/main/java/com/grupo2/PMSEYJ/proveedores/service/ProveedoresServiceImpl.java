package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosService;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosServiceImpl;
import com.grupo2.PMSEYJ.core.exception.*;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.ProductoPedidoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductoServiceImpl;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductosService;
import com.grupo2.PMSEYJ.proveedores.dao.*;
import com.grupo2.PMSEYJ.proveedores.dto.*;
import com.grupo2.PMSEYJ.proveedores.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresServiceImpl implements ProveedoresService{

    ProveedorDAO proveedorDAO = new ProveedorDAO();
    FacturaCompraDAO facturaCompraDAO = new FacturaCompraDAO();
    LoteDAO loteDAO = new LoteDAO();
    CotejoDAO cotejoDAO = new CotejoDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();
    DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
    ProductosService productosService = new ProductoServiceImpl();
    ParametrosService parametrosService = new ParametrosServiceImpl();
    @Override
    public boolean verificarExistenciaProveedor(String nombre) {
        Proveedor existe= proveedorDAO.consultarPorNombre(nombre);
        return existe != null;
    }

    @Override
    public void insertarProveedor(NuevoProveedorDTO nuevoProveedor) {

        if(verificarExistenciaProveedor(nuevoProveedor.getNombre_pro())){
            throw new ProveedorYaExisteException("Ya existe un proveedor con el nombre proporcionado");
        }

        if(!nuevoProveedor.getCorreo_pro().matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El correo electrónico no tiene formato de email válido");
        }

        if(!nuevoProveedor.getTelefono_pro().matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El número de teléfono celular es incorrecto, debe empezar por 09");
        }

        if(!nuevoProveedor.getNombre_pro().matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ&0-9 ]+$")){
            throw new NombreNoVálidoException("El nombre del proveedor contiene caracteres inválidos");
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setCorreo_pro(nuevoProveedor.getCorreo_pro());
        proveedor.setTelefono_pro(nuevoProveedor.getTelefono_pro());
        proveedor.setNombre_pro(nuevoProveedor.getNombre_pro());
        proveedor.setEstado_pv("A");

        proveedorDAO.insertar(proveedor);

    }

    @Override
    public GestionProveedorDTO consultarProveedorPorNombre(String nombre) {
        Proveedor proveedor = proveedorDAO.consultarPorNombre(nombre);
        if(proveedor == null){
            throw  new IllegalArgumentException("No existe el proveedor con el nombre proporcionado!");
        }

        return new GestionProveedorDTO(proveedor.getNombre_pro(),proveedor.getTelefono_pro(),proveedor.getCorreo_pro(), proveedor.getEstado_pv());
    }

    @Override
    public GestionProveedorDTO consultarProveedorPorID(Integer id_prove) {
        Proveedor proveedor = proveedorDAO.consultarPorID(id_prove);
        if(proveedor == null){
            throw  new IllegalArgumentException("No existe el proveedor con el nombre proporcionado!");
        }

        return new GestionProveedorDTO(proveedor.getNombre_pro(),proveedor.getTelefono_pro(),proveedor.getCorreo_pro(), proveedor.getEstado_pv());
    }

    @Override
    public LotePedidoDTO consultarLotePorId(Integer id_lote) {
        Lote lote = loteDAO.consultarPorId(id_lote);
        return new LotePedidoDTO(lote.getCodigo_barras(),lote.getNum_lote());
    }


    @Override
    public void actualizarCorreoPorNombre(String correo, String nombre) {
        if(!correo.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El nuevo correo electrónico no tiene formato de email válido");
        }

        proveedorDAO.actualizarCorreoPorNombre(correo,nombre);
    }

    @Override
    public void actualizarTelefonoPorNombre(String telefono, String nombre) {
        if(!telefono.matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El nuevo número de teléfono celular es incorrecto, debe empezar por 09");
        }

        proveedorDAO.actualizarTelefonoPorNombre(telefono,nombre);

    }

    @Override
    public void darDeAltaProveedor(String nombre) {
        proveedorDAO.darDeAlta("A" ,nombre);
    }

    @Override
    public void darDeBajaProveedor(String nombre) {
        proveedorDAO.darDeBaja("I" ,nombre);

    }

    @Override
    public Integer crearFacturaCompra(NuevaFacturaCompraDTO nuevaFacturaCompra) {

        if(verificarExistenciaFacturaCompra(nuevaFacturaCompra.getNum_fc(), nuevaFacturaCompra.getId_prove())){
            if(!verificarSiYaFueIngresada(nuevaFacturaCompra.getNum_fc(),nuevaFacturaCompra.getId_prove())){
                throw new FacturaYaIngresadaException("La factura del proveedor ya fue ingresada previamente pero no guardo su stock. Continue con el proceso");
            }else{
                throw new StockYaIngresadoException("La factura del proveedor ya fue ingresada previamente y el stock ya fue actualizado");
            }
        }

        if(!nuevaFacturaCompra.getNum_fc().matches("^\\d{3}-\\d{3}-\\d{9}$")){
            throw new NumeroDeFacturaNoValidaException("Número de factura no válida, la factura debe seguir el formato XXX-XXX-XXXXXXXXX");
        }

        FacturaCompra FacturaCompra = new FacturaCompra();
        FacturaCompra.setFecha_fc(nuevaFacturaCompra.getFecha_fc());
        FacturaCompra.setNum_fc(nuevaFacturaCompra.getNum_fc());
        FacturaCompra.setEstado("N");
        FacturaCompra.setFue_ingresada(false);
        FacturaCompra.setId_prove(nuevaFacturaCompra.getId_prove());
        facturaCompraDAO.insertar(FacturaCompra);
        return FacturaCompra.getId_fc();

    }

    @Override
    public boolean verificarEstadoFacturaCompra(String num_fc, Integer id_prove) {
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumeroYProveedor(num_fc,id_prove);
        return facturaCompra.getEstado().equals("N");
    }


    @Override
    public boolean verificarExistenciaFacturaCompra(String num_fc,Integer id_prove) {
        FacturaCompra FacturaCompra = facturaCompraDAO.consultarPorNumeroYProveedor(num_fc,id_prove);
        return FacturaCompra != null;
    }

    @Override
    public void agregarProducto(Integer id_fc , NuevoLoteDTO nuevoLote) {

        List<Lote> lotes = loteDAO.consultarPorCodigoBarras(nuevoLote.getCodigo_barras());
        try{
            Producto producto = productosService.consultarProductoPorCodigoBarras(nuevoLote.getCodigo_barras());
            for(Lote lote : lotes){
                if(lote.getNum_lote().equals(nuevoLote.getNum_lote())){
                    throw new LoteYaExisteException("Ya existe el lote: " + nuevoLote.getNum_lote() + " para el producto: " + producto.getNombre_p() );
                }
            }

            if(!nuevoLote.getNum_lote().matches("^[A-Za-z0-9-]{1,12}$")){
                throw new NumeroDeLoteNoValidoException("Lote inválido: El lote solo acepta letras, números y el -");
            }

            if(!nuevoLote.getFecha_vn().isAfter(LocalDate.now()) || nuevoLote.getFecha_vn().isEqual(LocalDate.now())){
                throw new FechaFormatoErroneoException("La fecha de vencimiento debe ser mayor a la fecha actual");
            }
            Lote lote = new Lote();
            lote.setCodigo_barras(nuevoLote.getCodigo_barras());
            lote.setTiene_iva(nuevoLote.getTiene_iva());
            lote.setRentabilidad(nuevoLote.getRentabilidad());
            lote.setFecha_vn(nuevoLote.getFecha_vn());
            lote.setPrecio_compra(nuevoLote.getPrecio_compra());
            lote.setTamano_caja(nuevoLote.getTamano_caja());
            lote.setNum_lote(nuevoLote.getNum_lote());

            Integer stock;

            if(producto.getForma_venta().equals("C")){
                stock = nuevoLote.getN_cajasCompradas();
            }else{
                stock = nuevoLote.getN_cajasCompradas() * nuevoLote.getTamano_caja();
            }

            lote.setStock(stock);
            lote.setEstado("N");
            loteDAO.insertar(lote);
            Cotejo cotejo = new Cotejo();
            cotejo.setCantidad(nuevoLote.getN_cajasCompradas());
            cotejo.setId_lote(lote.getId_lote());
            cotejo.setId_fc(id_fc);
            cotejoDAO.insertar(cotejo);
        }catch(ProductoNoExisteException e){
            throw new ProductoNoExisteException(e.getMessage() + " para poder ingresar mercadería de este producto");
        }


    }

    @Override
    public boolean verificarSiYaFueIngresada(String num_fc, Integer id_prove) {
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumeroYProveedor(num_fc,id_prove);
        return facturaCompra.getFue_ingresada();
    }

    @Override
    public void ingresarFacturaCompra(String num_fc, Integer id_prove) {
        if(verificarSiYaFueIngresada(num_fc,id_prove)){
            throw new FacturaYaIngresadaException("La factura  ya fue ingresada previamente");
        }
        facturaCompraDAO.ingresarAlSistemaPorNumeroYProveedor(true, num_fc,id_prove);
    }

    @Override
    public List<CotejoDTO> consultarProductosFacturaPendiente(String num_fc, Integer id_prove) {
        List<CotejoDTO> listaCotejoDTO = new ArrayList<>();
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumeroYProveedor(num_fc,id_prove);
        if(facturaCompra == null){
            GestionProveedorDTO proveedor = consultarProveedorPorID(id_prove);
            throw new FacturaNoExisteException("No existe una factura con el número: " + num_fc + "del proveedor" + proveedor.getNombre_pro());
        }
        List<Cotejo> cotejos =  cotejoDAO.consultarPorFactura(facturaCompra.getId_fc());
        for(Cotejo cotejo : cotejos){
            listaCotejoDTO.add(new CotejoDTO(cotejo.getId_fc(),cotejo.getId_lote(),cotejo.getCantidad()));
        }

        return listaCotejoDTO;
    }

    @Override
    public List<ProductoLoteDTO> armarTablaPendiente(List<CotejoDTO> pendientes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha_vn;
        List<ProductoLoteDTO> listaPendiente = new ArrayList<>();
        for(CotejoDTO cotejo : pendientes){
            Lote lote = loteDAO.consultarPorId(cotejo.getId_lote());
            Producto p = productosService.consultarProductoPorCodigoBarras(lote.getCodigo_barras());
            boolean seVendePorCaja = p.getForma_venta().equals("C");
            fecha_vn = lote.getFecha_vn().format(formatter);
            listaPendiente.add(new ProductoLoteDTO(lote.getCodigo_barras(),p.getNombre_p(),lote.getNum_lote(),fecha_vn,lote.getPrecio_compra(),lote.getTamano_caja(),lote.getRentabilidad(),seVendePorCaja,lote.getTiene_iva(), parametrosService.consultarValorIva()));
        }
        return listaPendiente;
    }

    @Override
    public FacturaCompraPendienteDTO consultarFacturaCompra(String num_fc,Integer id_prove) {
        FacturaCompra factura = facturaCompraDAO.consultarPorNumeroYProveedor(num_fc,id_prove);
        GestionProveedorDTO proveedor = consultarProveedorPorID(id_prove);
        if(factura == null){
            throw new FacturaNoExisteException("No existe una factura con el número: " + num_fc + " del proveedor: " + proveedor.getNombre_pro());
        }
        FacturaCompraPendienteDTO facturaPendiente = new FacturaCompraPendienteDTO();
        facturaPendiente.setId_fc(factura.getId_fc());
        facturaPendiente.setNum_fc(factura.getNum_fc());
        return facturaPendiente;
    }

    @Override
    public List<ProveedorDTO> consultarTodosLosProveedores() {
        List<ProveedorDTO> listaProveedorDTO = new ArrayList<>();
        List<Proveedor> proveedores = proveedorDAO.consultarTodos();
        for(Proveedor proveedor : proveedores){
            listaProveedorDTO.add(new ProveedorDTO(proveedor.getId_prove(), proveedor.getNombre_pro()));
        }
        return  listaProveedorDTO;

    }

    @Override
    public ProveedorDTO consultarProveedorNombre(String nombre) {
        Proveedor proveedor = proveedorDAO.consultarPorNombre(nombre);
        if(proveedor == null){
            throw  new IllegalArgumentException("No existe el proveedor con el nombre proporcionado!");
        }
        return new ProveedorDTO(proveedor.getId_prove(), proveedor.getNombre_pro());
    }

    @Override
    public Integer insertarPedido() {
        Pedido pedido = new Pedido();
        pedido.setEstado("N");
        pedido.setFecha_pv(LocalDate.now());
        pedido.setId_prove(null);
        pedidoDAO.insertar(pedido);
        return pedido.getId_pedido();

    }

    @Override
    public void insertarDetallePedido(DetallePedidoDTO nuevoDetallePedido) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setId_pedido(nuevoDetallePedido.getId_pedido());
        detallePedido.setCodigo_barras(nuevoDetallePedido.getCodigo_barras());
        detallePedido.setCantidad(nuevoDetallePedido.getCantidad());
        detallePedidoDAO.insertar(detallePedido);
    }

    @Override
    public DetallePedidoDTO consultarDetallePedido(Integer id_pedido, String codigo_barras) {
        DetallePedido detallePedido = detallePedidoDAO.consultarPorIdYCodigoBarras(id_pedido, codigo_barras);
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
        detallePedidoDTO.setId_pedido(detallePedido.getId_pedido());
        detallePedidoDTO.setCodigo_barras(detallePedido.getCodigo_barras());
        return detallePedidoDTO;
    }

    @Override
    public void actualizarDetallePedido(Integer cantidad, Integer id_pedido, String codigo_barras) {
        detallePedidoDAO.actualizarCantidadPorIdyCodigoBarras(cantidad, id_pedido, codigo_barras);
    }

    @Override
    public void eliminarDetallePedido(Integer id_pedido, String codigo_barras) {
        detallePedidoDAO.eliminar(id_pedido, codigo_barras);
    }

    @Override
    public List<ProductoPedidoDTO> cargarPedidoPendiente(Integer id_pedido) {
        List<DetallePedido> detallePedidos = detallePedidoDAO.consultarPorIdPedido(id_pedido);
        List<ProductoPedidoDTO> listaProductoDTO = new ArrayList<>();
        Integer numero_producto = 1;
        for(DetallePedido detallePedido : detallePedidos) {
            Producto producto = productosService.consultarProductoPorCodigoBarras(detallePedido.getCodigo_barras());
            ProductoPedidoDTO productoPedidoDTO = new ProductoPedidoDTO();
            productoPedidoDTO.setCantidad_cajas(detallePedido.getCantidad());
            productoPedidoDTO.setNumero_p(numero_producto);
            productoPedidoDTO.setCodigo_barras(detallePedido.getCodigo_barras());
            productoPedidoDTO.setNombre_p(producto.getNombre_p());
            listaProductoDTO.add(productoPedidoDTO);
        }
        return  listaProductoDTO;
    }

    @Override
    public NuevoPedidoDTO consultarPedidoPendiente() {
        Pedido pedidoPendiente = pedidoDAO.consultarPorEstado("N");
        if(pedidoPendiente == null){
            return null;
        }
        NuevoPedidoDTO nuevoPedido = new NuevoPedidoDTO();
        nuevoPedido.setId_pedido(pedidoPendiente.getId_pedido());
        return nuevoPedido;

    }

    @Override
    public void definirProveedorAPedido(Integer id_pedido, Integer id_proveedor) {
        pedidoDAO.definirProveedorAlPedido(id_pedido, id_proveedor);
    }

    @Override
    public void enviarPedido(Integer id_pedido, String estado) {
        pedidoDAO.enviarPedido(id_pedido, estado);
        pedidoDAO.definirFechaEnvio(id_pedido,LocalDate.now());
    }


}
