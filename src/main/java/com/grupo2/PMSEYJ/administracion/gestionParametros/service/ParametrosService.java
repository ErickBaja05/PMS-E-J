package com.grupo2.PMSEYJ.administracion.gestionParametros.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.FirmaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;

public interface ParametrosService {
    void guardarFirma(FirmaDTO firma);
    Double consultarValorIva();
    void actualizarValorIva(IvaDTO valor);

}
