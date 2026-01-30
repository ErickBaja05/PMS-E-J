package com.grupo2.PMSEYJ.proveedores.service;

import com.grupo2.PMSEYJ.core.exception.CelularNoValidoException;
import com.grupo2.PMSEYJ.core.exception.CorreoNoValidoException;
import com.grupo2.PMSEYJ.core.exception.NombreNoVálidoException;
import com.grupo2.PMSEYJ.core.exception.ProveedorYaExisteException;
import com.grupo2.PMSEYJ.proveedores.dao.ProveedorDAO;
import com.grupo2.PMSEYJ.proveedores.dto.ProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.model.Proveedor;

public class ProveedoresServiceImpl implements ProveedoresService{

    ProveedorDAO proveedorDAO = new ProveedorDAO();
    @Override
    public boolean verificarExistenciaProveedor(String nombre) {
        Proveedor existe= proveedorDAO.consultarPorNombre(nombre);
        return existe != null;
    }

    @Override
    public void insertarProveedor(ProveedorDTO nuevoProveedor) {

        if(verificarExistenciaProveedor(nuevoProveedor.getNombre_pro())){
            throw new ProveedorYaExisteException("Ya existe un proveedor con el nombre proporcionado");
        }

        if(!nuevoProveedor.getCorreo_pro().matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El correo electrónico no tiene formato de email válido");
        }

        if(!nuevoProveedor.getTelefono_pro().matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El número de teléfono celular es incorrecto, debe empezar por 09");
        }

        if(!nuevoProveedor.getNombre_pro().matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ&0-9 ]+$")){
            throw new NombreNoVálidoException("El nombre del proveedor contiene caracteres inválidos");
        }



        Proveedor proveedor = new Proveedor();
        proveedor.setCorreo_pro(nuevoProveedor.getCorreo_pro());
        proveedor.setTelefono_pro(nuevoProveedor.getTelefono_pro());
        proveedor.setNombre_pro(nuevoProveedor.getNombre_pro());

        proveedorDAO.insertar(proveedor);

    }

    @Override
    public ProveedorDTO consultarProveedorPorNombre(String nombre) {
        Proveedor proveedor = proveedorDAO.consultarPorNombre(nombre);
        if(proveedor == null){
            throw  new IllegalArgumentException("No existe el proveedor con el nombre proporcionado!");
        }

        return new ProveedorDTO(proveedor.getNombre_pro(),proveedor.getTelefono_pro(),proveedor.getCorreo_pro());
    }

    @Override
    public void actualizarCorreoPorNombre(String correo, String nombre) {
        if(!correo.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new CorreoNoValidoException("El nuevo correo electrónico no tiene formato de email válido");
        }

        proveedorDAO.actualizarCorreoPorNombre(correo,nombre);
    }

    @Override
    public void actualizarTelefonoPorNombre(String telefono, String nombre) {
        if(!telefono.matches("^09\\d{8}$")){
            throw new CelularNoValidoException("El nuevo número de teléfono celular es incorrecto, debe empezar por 09");
        }

        proveedorDAO.actualizarTelefonoPorNombre(telefono,nombre);

    }

    @Override
    public void eliminarProveedorPorNombre(String nombre) {
        proveedorDAO.eliminarProveedor(nombre);
    }
}
