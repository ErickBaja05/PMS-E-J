package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosService;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosServiceImpl;
import com.grupo2.PMSEYJ.core.exception.*;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductoServiceImpl;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductosService;
import com.grupo2.PMSEYJ.proveedores.dao.CotejoDAO;
import com.grupo2.PMSEYJ.proveedores.dao.FacturaCompraDAO;
import com.grupo2.PMSEYJ.proveedores.dao.LoteDAO;
import com.grupo2.PMSEYJ.proveedores.dao.ProveedorDAO;
import com.grupo2.PMSEYJ.proveedores.dto.*;
import com.grupo2.PMSEYJ.proveedores.model.Cotejo;
import com.grupo2.PMSEYJ.proveedores.model.FacturaCompra;
import com.grupo2.PMSEYJ.proveedores.model.Lote;
import com.grupo2.PMSEYJ.proveedores.model.Proveedor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresServiceImpl implements ProveedoresService{

    ProveedorDAO proveedorDAO = new ProveedorDAO();
    FacturaCompraDAO facturaCompraDAO = new FacturaCompraDAO();
    LoteDAO loteDAO = new LoteDAO();
    CotejoDAO cotejoDAO = new CotejoDAO();
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

        if(verificarExistenciaFacturaCompra(nuevaFacturaCompra.getNum_fc())){
            if(!verificarSiYaFueIngresada(nuevaFacturaCompra.getNum_fc())){
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
        facturaCompraDAO.insertar(FacturaCompra);
        return FacturaCompra.getId_fc();

    }

    @Override
    public boolean verificarEstadoFacturaCompra(String num_fc) {
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumero(num_fc);
        return facturaCompra.getEstado().equals("N");
    }


    @Override
    public boolean verificarExistenciaFacturaCompra(String num_fc) {
        FacturaCompra FacturaCompra = facturaCompraDAO.consultarPorNumero(num_fc);
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
    public boolean verificarSiYaFueIngresada(String num_fc) {
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumero(num_fc);
        return facturaCompra.getFue_ingresada();
    }

    @Override
    public void ingresarFacturaCompra(String num_fc) {
        if(verificarSiYaFueIngresada(num_fc)){
            throw new FacturaYaIngresadaException("La factura compra ya fue ingresada previamente");
        }
        facturaCompraDAO.ingresarAlSistemaPorNumero(true, num_fc);
    }

    @Override
    public List<CotejoDTO> consultarProductosFacturaPendiente(String num_fc) {
        List<CotejoDTO> listaCotejoDTO = new ArrayList<>();
        FacturaCompra facturaCompra = facturaCompraDAO.consultarPorNumero(num_fc);
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
    public FacturaCompraPendienteDTO consultarFacturaCompra(String num_fc) {
        FacturaCompra factura = facturaCompraDAO.consultarPorNumero(num_fc);
        FacturaCompraPendienteDTO facturaPendiente = new FacturaCompraPendienteDTO();
        facturaPendiente.setId_fc(factura.getId_fc());
        facturaPendiente.setNum_fc(factura.getNum_fc());
        return facturaPendiente;
    }


}
