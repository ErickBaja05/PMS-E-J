package com.grupo2.PMSEYJ.administracion.gestionUsuarios.service;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.InfoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.NuevoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;

public interface UsuarioService {

    UsuarioSesionDTO login(String usuario, String password);
    void insertarUsuario(NuevoUsuarioDTO nuevoUsuario);
    InfoUsuarioDTO consultarUsuarioPorNombre(String nombre);
    InfoUsuarioDTO consultarUsuarioPorEmail(String nombre);
    void eliminarUsuario(InfoUsuarioDTO infoUsuario);
}
