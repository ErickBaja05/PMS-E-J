package com.grupo2.PMSEYJ.clientes.service;

import com.grupo2.PMSEYJ.clientes.dao.ClienteNaturalDAO;
import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteNaturalDTO;
import com.grupo2.PMSEYJ.clientes.model.ClienteNatural;
import com.grupo2.PMSEYJ.core.exception.CedulaNoValidaException;
import com.grupo2.PMSEYJ.core.exception.ClienteYaExisteException;
import com.grupo2.PMSEYJ.core.exception.FechaNacimientoInvalidaException;

import java.time.LocalDate;

public class ClienteNaturalServiceImpl implements ClienteNaturalService {

    private final ClienteNaturalDAO clienteNaturalDAO = new ClienteNaturalDAO();


    @Override
    public void insertarClienteNatural(NuevoClienteNaturalDTO clienteNatural) {
        ClienteNatural existeCliente = clienteNaturalDAO.consultarPorCedula(clienteNatural.getCedula());
        if (existeCliente != null) {
            throw new ClienteYaExisteException("Ya existe un cliente con la cédula proporcionada!");
        }

        if (clienteNatural.getFecha_nacimiento().isAfter(LocalDate.now()) || clienteNatural.getFecha_nacimiento().isEqual(LocalDate.now())) {
            throw new FechaNacimientoInvalidaException("La fecha de nacimiento debe ser menor a la fecha actual");
        }

        if(clienteNatural.getCedula().charAt(2) <= 6){
            throw new IllegalArgumentException("El tercer digito de la cédula debe ser menor a 6");

        }

        if(!validarCedula(clienteNatural.getCedula())){
            throw new CedulaNoValidaException("Error en el número de cédula de identidad");

        }




        ClienteNatural nuevoCliente = new ClienteNatural();
        nuevoCliente.setCedula_cn(clienteNatural.getCedula());
        nuevoCliente.setNombre_cn(clienteNatural.getNombre());
        nuevoCliente.setCorreo_cn(clienteNatural.getCorreo());
        nuevoCliente.setDireccion_cn(clienteNatural.getDireccion());
        nuevoCliente.setTelefono_cn(clienteNatural.getTelefono());
        nuevoCliente.setFecha_cn(clienteNatural.getFecha_nacimiento());
        nuevoCliente.setEstado_cn("A");
        clienteNaturalDAO.insertar(nuevoCliente);

    }
    public static boolean validarProvincia(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false; // Debe tener exactamente 10 dígitos numéricos
        }

        int codigoProvincia = Integer.parseInt(cedula.substring(0, 2));

        // Validar rango 01-24 o igual a 30
        return (codigoProvincia >= 1 && codigoProvincia <= 24) || codigoProvincia == 30;
    }


    private boolean validarCedula(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false; // Debe tener exactamente 10 dígitos numéricos
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if ((i + 1) % 2 != 0) { // Posición impar (1, 3, 5, 7, 9)
                int producto = digito * 2;
                if (producto > 9) {
                    producto -= 9;
                }
                suma += producto;
            } else { // Posición par (2, 4, 6, 8)
                suma += digito;
            }
        }

        int residuo = suma % 10;
        int digitoVerificadorCalculado = (residuo == 0) ? 0 : (10 - residuo);
        int digitoVerificadorReal = Character.getNumericValue(cedula.charAt(9));

        validarProvincia(cedula);

        return (digitoVerificadorCalculado == digitoVerificadorReal) && validarProvincia(cedula) ;
    }



}
