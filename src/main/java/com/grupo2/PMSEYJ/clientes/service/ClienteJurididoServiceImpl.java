package com.grupo2.PMSEYJ.clientes.service;

import com.grupo2.PMSEYJ.clientes.dao.ClienteJuridicoDAO;
import com.grupo2.PMSEYJ.clientes.dto.GestionClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.model.ClienteJuridico;
import com.grupo2.PMSEYJ.core.exception.CelularNoValidoException;
import com.grupo2.PMSEYJ.core.exception.ClienteYaExisteException;

public class ClienteJurididoServiceImpl implements ClienteJuridicoService {

    private ClienteJuridicoDAO clienteJuridicoDAO = new ClienteJuridicoDAO();

    @Override
    public void insertarClienteJuridico(NuevoClienteJuridicoDTO clienteJuridico) {
        ClienteJuridico existeCliente = clienteJuridicoDAO.consultarPorRuc(clienteJuridico.getRuc());
        if(existeCliente != null){
            throw new ClienteYaExisteException("Ya existe un cliente con ese RUC");
        }
        if(!validarRuc(clienteJuridico.getRuc())){
            throw new ClienteYaExisteException("Error en el número de RUC");
        }

        if(!clienteJuridico.getTelefono_cj().matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El número de celular debe comenzar con 09");
        }
        ClienteJuridico nuevoCliente = new ClienteJuridico();
        nuevoCliente.setRuc(clienteJuridico.getRuc());
        nuevoCliente.setCorreo_cj(clienteJuridico.getCorreo_cj());
        nuevoCliente.setDireccion_cj(clienteJuridico.getDireccion_cj());
        nuevoCliente.setTelefono_cj(clienteJuridico.getTelefono_cj());
        nuevoCliente.setRazon_social(clienteJuridico.getRazon_social());
        nuevoCliente.setEstado_cj("A");
        clienteJuridicoDAO.insertar(nuevoCliente);



    }

    @Override
    public GestionClienteJuridicoDTO consultarClienteJuridico(String RUC) {
        ClienteJuridico cliente = clienteJuridicoDAO.consultarPorRuc(RUC);
        String parsingEstado;
        if(cliente == null){
            throw new IllegalArgumentException("No existe un cliente con el RUC proporcionado");
        }
        if(cliente.getEstado_cj().equals("A")){
            parsingEstado = "ACTIVO";
        }else{
            parsingEstado = "INACTIVO";
        }
        return new GestionClienteJuridicoDTO(
                cliente.getRuc(),
                cliente.getRazon_social(),
                cliente.getCorreo_cj(),
                cliente.getTelefono_cj(),
                cliente.getDireccion_cj(),
                parsingEstado
        );
    }

    @Override
    public void actualizarCorreo(String RUC, String correo) {
        clienteJuridicoDAO.actualizarCorreoPorRUC(RUC, correo);

    }

    @Override
    public void actualizarDireccion(String cedula, String direccion) {
        clienteJuridicoDAO.actualizarDireccionPorRUC(cedula, direccion);

    }

    @Override
    public void actualizarTelefono(String cedula, String telefono) {
        clienteJuridicoDAO.actualizarTelefonoPorRUC(cedula, telefono);

    }

    @Override
    public void darDeBaja(String RUC) {
        clienteJuridicoDAO.darDeBaja(RUC);
    }

    @Override
    public void darDeAlta(String RUC) {
        clienteJuridicoDAO.darDeAlta(RUC);
    }

    public static boolean validarProvincia(String numero) {
        if (numero == null || !numero.matches("\\d{10}|\\d{13}")) {
            return false; // Debe tener 10 o 13 dígitos numéricos
        }

        int codigoProvincia = Integer.parseInt(numero.substring(0, 2));

        // Validar rango 01-24 o igual a 30
        return (codigoProvincia >= 1 && codigoProvincia <= 24) || codigoProvincia == 30;
    }

    public static boolean validarRuc(String ruc) {
        if (ruc == null || !ruc.matches("\\d{13}")) {
            return false; // Debe tener exactamente 13 dígitos numéricos
        }

        // Validar código de establecimiento (últimos 3 dígitos)
        String establecimiento = ruc.substring(10, 13);
        if (!establecimiento.equals("001")) {
            return false;
        }

        // Coeficientes para Módulo 11
        int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
        int suma = 0;

        for (int i = 0; i < coeficientes.length; i++) {
            int digito = Character.getNumericValue(ruc.charAt(i));
            suma += digito * coeficientes[i];
        }

        int residuo = suma % 11;
        int verificadorCalculado = 11 - residuo;

        if (verificadorCalculado == 11) {
            verificadorCalculado = 0;
        } else if (verificadorCalculado == 10) {
            return false; // Dígito inválido según normativa
        }

        int verificadorReal = Character.getNumericValue(ruc.charAt(9));
        return verificadorCalculado == verificadorReal && validarProvincia(ruc);
    }

}
