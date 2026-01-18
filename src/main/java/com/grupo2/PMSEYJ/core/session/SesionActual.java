package com.grupo2.PMSEYJ.core.session;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;

public class SesionActual {

    private static UsuarioSesionDTO usuario;

    private SesionActual() {
        // Evita instanciación
    }

    public static void iniciarSesion(UsuarioSesionDTO usuarioSesion) {
        usuario = usuarioSesion;
    }

    public static UsuarioSesionDTO getUsuario() {
        return usuario;
    }

    public static boolean haySesion() {
        return usuario != null;
    }

    public static void cerrarSesion() {
        usuario = null;
    }
}
