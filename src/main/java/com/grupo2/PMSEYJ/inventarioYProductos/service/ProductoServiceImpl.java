package com.grupo2.PMSEYJ.inventarioYProductos.service;

import com.grupo2.PMSEYJ.core.exception.CodigoDeBarrasOAuxiliarNoValidoException;
import com.grupo2.PMSEYJ.core.exception.NombreNoVálidoException;
import com.grupo2.PMSEYJ.core.exception.ProductoYaExisteException;
import com.grupo2.PMSEYJ.inventarioYProductos.dao.IndiceTerapeuticoDAO;
import com.grupo2.PMSEYJ.inventarioYProductos.dao.LaboratorioDAO;
import com.grupo2.PMSEYJ.inventarioYProductos.dao.ProductoDAO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoIndiceTerapeuticoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoLaboratorioDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoProductoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.model.IndiceTerapeutico;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Laboratorio;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;

public class ProductoServiceImpl implements ProductosService{

    private final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final IndiceTerapeuticoDAO indiceTerapeuticoDAO = new IndiceTerapeuticoDAO();
    @Override
    public void insertarLaboratorio(NuevoLaboratorioDTO nuevoLaboratorio) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre_lab(nuevoLaboratorio.getNombre_lab());
        if(verificarExistenciaLaboratorio(nuevoLaboratorio.getNombre_lab())){
            return;
        }

        laboratorioDAO.insertar(laboratorio);

    }

    @Override
    public void insertarIndiceTerapeutico(NuevoIndiceTerapeuticoDTO nuevoIndiceTerapeutico) {
        IndiceTerapeutico indiceTerapeutico = new IndiceTerapeutico();
        indiceTerapeutico.setNombre_indice(nuevoIndiceTerapeutico.getNombre_indice());
        if(verificarExistenciaIndiceTerapeutico(nuevoIndiceTerapeutico.getNombre_indice())){
            return;
        }
        indiceTerapeuticoDAO.insertar(indiceTerapeutico);


    }

    @Override
    public boolean verificarExistenciaLaboratorio(String nombreLaboratorio) {
        Laboratorio laboratorio = laboratorioDAO.consultarPorNombre(nombreLaboratorio);
        return laboratorio != null;

    }

    @Override
    public void insertarProducto(NuevoProductoDTO nuevoProducto, NuevoLaboratorioDTO nuevoLaboratorio, NuevoIndiceTerapeuticoDTO nuevoIndiceTerapeutico) {

        if(verificarExistenciaProductoBarras(nuevoProducto.getCodigo_barras())) {
            throw new ProductoYaExisteException("Ya existe un producto con el código de barras proporcionado");
        }

        if(verificarExistenciaProductoAux(nuevoProducto.getCodigo_aux())){
            throw new ProductoYaExisteException("Ya existe un producto con el código auxiliar proporcionado");
        }

        if(!nuevoProducto.getCodigo_barras().matches("^\\d{1,13}$")){
            throw new CodigoDeBarrasOAuxiliarNoValidoException("El código de barras no puede exceder los 13 caracteres");
        }

        if(!nuevoProducto.getCodigo_aux().matches("^[A-Za-z0-9Ññ-]{1,15}$")){
            throw new CodigoDeBarrasOAuxiliarNoValidoException("El código auxiliar contiene caracteres inválidos");
        }

        if(!nuevoProducto.getNombre_p().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ _&\\-/()]{5,150}$")){
            throw new NombreNoVálidoException("El nombre del producto contiene símbolos no permitidos");
        }

        if(!nuevoProducto.getDescripcion().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ,.]{5,200}$")){
            throw new IllegalArgumentException("La descripción del producto contiene símbolos no permitidos");
        }

       if(!nuevoLaboratorio.getNombre_lab().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ,.&_\\-()/]{5,50}$")){
           throw new IllegalArgumentException("El nombre del laboratorio contiene símbolos no permitidos");
       }

       if(!nuevoIndiceTerapeutico.getNombre_indice().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ,./()%]{5,100}$")){
           throw new IllegalArgumentException("El índice terapéutico contiene símbolos");
       }



        Producto p = new Producto();
        Laboratorio laboratorio;
        IndiceTerapeutico indiceTerapeutico;

        p.setCodigo_barras(nuevoProducto.getCodigo_barras());
        p.setCodigo_aux(nuevoProducto.getCodigo_aux());
        p.setNombre_p(nuevoProducto.getNombre_p());
        p.setDescripcion(nuevoProducto.getDescripcion());
        p.setCategoria(nuevoProducto.getCategoria());
        p.setForma_venta(nuevoProducto.getForma_venta());
        p.setTipo_venta(nuevoProducto.getTipo_venta());
        p.setPvp(nuevoProducto.getPvp());

        if(!verificarExistenciaLaboratorio(nuevoLaboratorio.getNombre_lab())) {
            insertarLaboratorio(nuevoLaboratorio);
        }

        if(!verificarExistenciaIndiceTerapeutico(nuevoIndiceTerapeutico.getNombre_indice())) {
            insertarIndiceTerapeutico(nuevoIndiceTerapeutico);
        }

        laboratorio = laboratorioDAO.consultarPorNombre(nuevoLaboratorio.getNombre_lab());
        indiceTerapeutico = indiceTerapeuticoDAO.consultarPorNombre(nuevoIndiceTerapeutico.getNombre_indice());
        p.setId_lab(laboratorio.getId_lab());
        p.setId_indice_t(indiceTerapeutico.getId_indice_terapeutico());

        productoDAO.insertar(p);
    }

    @Override
    public boolean verificarExistenciaProductoBarras(String codigoBarras) {
        return productoDAO.consultarPorCodBarras(codigoBarras) != null;
    }

    @Override
    public boolean verificarExistenciaProductoAux(String codigoAux) {
        return productoDAO.consultarPorCodAux(codigoAux) != null;
    }



    @Override
    public boolean verificarExistenciaIndiceTerapeutico(String nombreIndiceTerapeutico) {
        return indiceTerapeuticoDAO.consultarPorNombre(nombreIndiceTerapeutico) != null;
    }
}
