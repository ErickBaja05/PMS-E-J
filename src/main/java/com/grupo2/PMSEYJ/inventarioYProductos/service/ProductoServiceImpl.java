package com.grupo2.PMSEYJ.inventarioYProductos.service;

import com.grupo2.PMSEYJ.core.exception.CodigoDeBarrasNoValidoException;
import com.grupo2.PMSEYJ.core.exception.ProductoYaExisteException;
import com.grupo2.PMSEYJ.inventarioYProductos.dao.LaboratorioDAO;
import com.grupo2.PMSEYJ.inventarioYProductos.dao.ProductoDAO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoLaboratorioDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoProductoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Laboratorio;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;

public class ProductoServiceImpl implements ProductosService{

    private final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    @Override
    public void insertarLaboratorio(NuevoLaboratorioDTO nuevoLaboratorio) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre_lab(nuevoLaboratorio.getNombre_lab());
        if(verificarExistenciaLaboratorio(nuevoLaboratorio.getNombre_lab())){
            return;
        };

        laboratorioDAO.insertar(laboratorio);

    }

    @Override
    public boolean verificarExistenciaLaboratorio(String nombreLaboratorio) {
        Laboratorio laboratorio = laboratorioDAO.consultarPorNombre(nombreLaboratorio);
        return laboratorio != null;

    }

    @Override
    public void insertarProducto(NuevoProductoDTO nuevoProducto, NuevoLaboratorioDTO nuevoLaboratorio) {

        if(verificarExistenciaProducto(nuevoProducto.getCodigo_aux())){
            throw new ProductoYaExisteException("Ya existe un producto con el código auxiliar proporcionado");
        }

        if(!nuevoProducto.getCodigo_br().matches("^\\d{1,13}$")){
            throw new CodigoDeBarrasNoValidoException("El código de barras no puede exceder los 13 caracteres");
        }

        Producto p = new Producto();
        Laboratorio laboratorio;

        p.setCodigo_aux(nuevoProducto.getCodigo_aux());
        p.setCodigo_br(nuevoProducto.getCodigo_br());
        p.setNombre_p(nuevoProducto.getNombre_p());
        p.setDescripcion(nuevoProducto.getDescripcion());
        p.setCategoria(nuevoProducto.getCategoria());
        p.setForma_venta(nuevoProducto.getForma_venta());
        p.setTipo_venta(nuevoProducto.getTipo_venta());
        p.setPvp(nuevoProducto.getPvp());
        p.setIndice_t(nuevoProducto.getIndice_t());

        if(!verificarExistenciaLaboratorio(nuevoLaboratorio.getNombre_lab())) {
            insertarLaboratorio(nuevoLaboratorio);
        }

        laboratorio = laboratorioDAO.consultarPorNombre(nuevoLaboratorio.getNombre_lab());
        p.setId_lab(laboratorio.getId_lab());

        productoDAO.insertar(p);
    }

    @Override
    public boolean verificarExistenciaProducto(String codAux) {
        return productoDAO.consultarPorCodAux(codAux) != null;
    }
}
