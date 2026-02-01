package com.grupo2.PMSEYJ.administracion.gestionParametros.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dao.FirmaDAO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dao.IvaDAO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dao.MetodoPagoDAO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.FirmaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.GestionMetodoPagoDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.IVA;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.MetodoPago;

import java.util.ArrayList;
import java.util.List;


public class ParametrosServiceImpl implements ParametrosService {

    private FirmaDAO firmaDAO = new FirmaDAO();
    private IvaDAO ivaDAO = new IvaDAO();
    private MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();

    @Override
    public void guardarFirma(FirmaDTO firma) {
        Firma firma1 = new Firma();
        firma1.setRuta_fr(firma.getRuta_fr());
        firmaDAO.insertar(firma1);

    }

    @Override
    public Double consultarValorIva() {
        IVA valor = ivaDAO.consultarValor();
        if (valor == null) {
            throw new RuntimeException("No se ha definido un valor de IVA");
        }
        return valor.getValorActual();
    }

    @Override
    public void actualizarValorIva(IvaDTO valor) {
        IVA newValor = new IVA(ivaDAO.consultarValor().getValorActual());
        newValor.setValorNuevo(valor.getNuevo_valor());
        ivaDAO.actualizar(newValor);

    }

    @Override
    public List<GestionMetodoPagoDTO> consultarMetodosPago() {
        List<MetodoPago> metodosPago = metodoPagoDAO.listarTodos();
        List<GestionMetodoPagoDTO> gestionMetodosPago = new ArrayList<>();

        if (metodosPago == null) {
            throw new RuntimeException("No existen métodos de pago, comuníquese con el administrador del sistema");
        }

        for (MetodoPago metodoPago : metodosPago) {
            // PARSING A LOS ESTADOS
            if(metodoPago.getEstado_pago().equals("A")){
                metodoPago.setEstado_pago("HABILITADO");
            }else{
                metodoPago.setEstado_pago("DESHABILITADO");
            }
            gestionMetodosPago.add(new GestionMetodoPagoDTO(metodoPago.getNombre_pago(), metodoPago.getEstado_pago()));
        }

        return gestionMetodosPago;
    }

    @Override
    public void habilitarMetodoPago(String nombre_pago) {

        metodoPagoDAO.actualizarEstadoPorNombre(nombre_pago,"A");
    }

    @Override
    public void deshabilitarMetodoPago(String nombre_pago) {
        metodoPagoDAO.actualizarEstadoPorNombre(nombre_pago,"I");
    }
}
