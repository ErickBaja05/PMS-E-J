package com.grupo2.PMSEYJ.administracion.gestionParametros.service;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.FirmaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.GestionMetodoPagoDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;

import java.util.List;

public interface ParametrosService {
    void guardarFirma(FirmaDTO firma);
    Double consultarValorIva();
    void actualizarValorIva(IvaDTO valor);
    List<GestionMetodoPagoDTO> consultarMetodosPago();
    void habilitarMetodoPago(String nombre_pago);
    void deshabilitarMetodoPago(String nombre_pago);

}
