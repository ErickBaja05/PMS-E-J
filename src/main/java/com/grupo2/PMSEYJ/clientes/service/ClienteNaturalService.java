package com.grupo2.PMSEYJ.clientes.service;

import com.grupo2.PMSEYJ.clientes.dto.GestionClienteNaturalDTO;
import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteNaturalDTO;


public interface ClienteNaturalService {

    void insertarClienteNatural(NuevoClienteNaturalDTO clienteNatural);
    GestionClienteNaturalDTO consultarCliente(String cedula);
    void actualizarCorreo(String cedula, String correo);
    void actualizarDireccion(String cedula, String direccion);
    void actualizarTelefono(String cedula, String telefono);
    void darDeBaja(String cedula);
    void darDeAlta(String cedula);


}
