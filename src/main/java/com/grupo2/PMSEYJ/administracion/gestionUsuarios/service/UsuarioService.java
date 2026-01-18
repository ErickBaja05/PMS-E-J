package com.grupo2.PMSEYJ.administracion.gestionUsuarios.service;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;

public interface UsuarioService {

    UsuarioSesionDTO login(String usuario, String password);
}
