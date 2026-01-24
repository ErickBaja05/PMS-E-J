package com.grupo2.PMSEYJ.administracion.gestionUsuarios.service;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.NuevoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dao.UsuarioDAO;
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();


    @Override
    public UsuarioSesionDTO login(String username, String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        Usuario usuario = usuarioDAO.buscarPorNombre(username);

        if (usuario == null || !usuario.getPassword().equals(password)) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos.");
        }


        return new UsuarioSesionDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getPerfil()
        );
    }

    @Override
    public boolean insertarUsuario(NuevoUsuarioDTO nuevoUsuario) {
        Usuario existenteNombre = usuarioDAO.buscarPorNombre(nuevoUsuario.getNombre());
        Usuario existeCorreo = usuarioDAO.buscarPorEmail(nuevoUsuario.getCorreo());
        if (existenteNombre != null  && existeCorreo != null) {
            return false;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setPassword(nuevoUsuario.getPassword());
        usuario.setCorreo(nuevoUsuario.getCorreo());
        usuario.setPerfil(nuevoUsuario.getPerfil());
        usuarioDAO.insertar(usuario);
        return true;



    }
}
