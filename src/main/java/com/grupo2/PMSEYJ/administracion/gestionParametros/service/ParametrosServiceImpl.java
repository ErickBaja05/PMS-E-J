package com.grupo2.PMSEYJ.administracion.gestionParametros.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dao.FirmaDAO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dao.IvaDAO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.FirmaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.IVA;


public class ParametrosServiceImpl implements ParametrosService {

    private FirmaDAO firmaDAO = new FirmaDAO();
    private IvaDAO ivaDAO = new IvaDAO();

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
}
