package com.grupo2.PMSEYJ.clientes.service;

import com.grupo2.PMSEYJ.clientes.dao.ClienteJuridicoDAO;
import com.grupo2.PMSEYJ.clientes.dto.GestionClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.model.ClienteJuridico;
import com.grupo2.PMSEYJ.core.exception.*;

public class ClienteJurididoServiceImpl implements ClienteJuridicoService {

    private ClienteJuridicoDAO clienteJuridicoDAO = new ClienteJuridicoDAO();

    @Override
    public void insertarClienteJuridico(NuevoClienteJuridicoDTO clienteJuridico) {
        ClienteJuridico existeCliente = clienteJuridicoDAO.consultarPorRuc(clienteJuridico.getRuc());
        if(existeCliente != null){
            throw new ClienteYaExisteException("Ya existe un cliente con el RUC proporcionado!");
        }
        if(!validarRuc(clienteJuridico.getRuc())){
            throw new CedulaNoValidaException("Error en el número de RUC, no cumple con el formato ecuatoriano válido");
        }

        if(!clienteJuridico.getTelefono_cj().matches("^09\\d{8}$")){
            throw new CelularNoValidoException("Número de teléfono celular no válido, el número ingresado no empieza por 09");
        }


        if(!clienteJuridico.getRazon_social().matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ&0-9 ]+$")){
            throw new NombreNoVálidoException("Razón social no válida, contiene caracteres no permitidos");
        }

        if(!clienteJuridico.getCorreo_cj().matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El correo electrónico no tiene formato válido");
        }

        if(!clienteJuridico.getDireccion_cj().matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ0-9 .,_-]+$")){
            throw new DireccionNoValidaException("Dirección no válida, la nueva dirección fiscal contiene caracteres no válidos”");
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
        if(!correo.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El nuevo correo electrónico no tiene formato válido");
        }
        clienteJuridicoDAO.actualizarCorreoPorRUC(RUC, correo);

    }

    @Override
    public void actualizarDireccion(String cedula, String direccion) {
        if(!direccion.matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ0-9 .,_-]+$")){
            throw new DireccionNoValidaException("Dirección no válida, la nueva dirección fiscal contiene caracteres no válidos");
        }
        clienteJuridicoDAO.actualizarDireccionPorRUC(cedula, direccion);

    }

    @Override
    public void actualizarTelefono(String cedula, String telefono) {
        if(!telefono.matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El nuevo número de teléfono celular es incorrecto, debe empezar por 09 ");
        }

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

    private static boolean validarCedula(String cedula) {
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

    public static boolean validarRuc(String ruc) {
        if (ruc == null || !ruc.matches("\\d{13}")) {
            return false; // Debe tener exactamente 13 dígitos numéricos
        }

        // Validar código de establecimiento (últimos 3 dígitos)
        String establecimiento = ruc.substring(10, 13);
        if (!establecimiento.equals("001")) {
            return false;
        }

        // Validar provincia
        if (!validarProvincia(ruc)) {
            return false;
        }

        // El tercer dígito define el tipo de contribuyente
        int tercerDigito = Character.getNumericValue(ruc.charAt(2));

        if (tercerDigito < 6) {
            // Persona natural: validar la cédula (primeros 10 dígitos)
            String cedula = ruc.substring(0, 10);
            return validarCedula(cedula);
        } else if (tercerDigito == 9) {
            // Sociedad privada: módulo 11 con coeficientes {4,3,2,7,6,5,4,3,2}
            int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
            int suma = 0;
            for (int i = 0; i < coeficientes.length; i++) {
                int digito = Character.getNumericValue(ruc.charAt(i));
                suma += digito * coeficientes[i];
            }
            int residuo = suma % 11;
            int verificadorCalculado = 11 - residuo;
            if (verificadorCalculado == 11) verificadorCalculado = 0;
            if (verificadorCalculado == 10) return false;

            int verificadorReal = Character.getNumericValue(ruc.charAt(9));
            return verificadorCalculado == verificadorReal;
        } else if (tercerDigito == 6) {
            // Entidad pública: módulo 11 con coeficientes {3,2,7,6,5,4,3,2}
            int[] coeficientes = {3, 2, 7, 6, 5, 4, 3, 2};
            int suma = 0;
            for (int i = 0; i < coeficientes.length; i++) {
                int digito = Character.getNumericValue(ruc.charAt(i));
                suma += digito * coeficientes[i];
            }
            int residuo = suma % 11;
            int verificadorCalculado = 11 - residuo;
            if (verificadorCalculado == 11) verificadorCalculado = 0;
            if (verificadorCalculado == 10) return false;

            int verificadorReal = Character.getNumericValue(ruc.charAt(8)); // ojo: posición distinta
            return verificadorCalculado == verificadorReal;
        }

        return false; // No corresponde a ningún tipo válido
    }



}
