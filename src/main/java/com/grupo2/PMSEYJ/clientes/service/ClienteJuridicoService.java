package com.grupo2.PMSEYJ.clientes.service;

import com.grupo2.PMSEYJ.clientes.dto.GestionClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteJuridicoDTO;

public interface ClienteJuridicoService {
    void insertarClienteJuridico(NuevoClienteJuridicoDTO clienteJuridico);
    GestionClienteJuridicoDTO consultarClienteJuridico(String RUC);
    void actualizarCorreo(String RUC, String correo);
    void actualizarDireccion(String RUC, String direccion);
    void actualizarTelefono(String RUC, String telefono);
    void darDeBaja(String RUC);
    void darDeAlta(String RUC);
}
