package com.grupo2.PMSEYJ.administracion.gestionUsuarios.service;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.InfoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.NuevoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dao.UsuarioDAO;
import com.grupo2.PMSEYJ.core.exception.CorreoNoValidoException;
import com.grupo2.PMSEYJ.core.exception.PasswordNoValidoException;
import com.grupo2.PMSEYJ.core.exception.UsuarioYaExisteException;

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
    public void insertarUsuario(NuevoUsuarioDTO nuevoUsuario) {
        Usuario existenteNombre = usuarioDAO.buscarPorNombre(nuevoUsuario.getNombre());
        Usuario existeCorreo = usuarioDAO.buscarPorEmail(nuevoUsuario.getCorreo());
        if(existenteNombre != null) {
            throw new UsuarioYaExisteException("Ya existe un usuario con el nombre de usuario proporcionado!");
        }
        if(existeCorreo != null) {
            throw new UsuarioYaExisteException("Ya existe un usuario con el correo proporcionado!");
        }

        if (!nuevoUsuario.getCorreo().matches("\"^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,16}$\"")) {
            throw new CorreoNoValidoException("El correo tiene formato inválido");
        }

        if(!nuevoUsuario.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9])[A-Za-z\\d\\W]{8,16}$")){
            throw  new PasswordNoValidoException("La contraseña debe tener de entre 8 y 16 caracteres con al menos una minúscula, una mayúscula y un carácter especial");

        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setPassword(nuevoUsuario.getPassword());
        usuario.setCorreo(nuevoUsuario.getCorreo());
        usuario.setPerfil(nuevoUsuario.getPerfil());
        usuarioDAO.insertar(usuario);

    }

    @Override
    public InfoUsuarioDTO consultarUsuarioPorNombre(String nombre) {
        Usuario usuario = usuarioDAO.buscarPorNombre(nombre);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe un usuario con el nombre de usuario proporcionado");
        }

        return new InfoUsuarioDTO(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getPerfil()
        );

    }

    @Override
    public InfoUsuarioDTO consultarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe un usuario con el nombre de usuario proporcionado");
        }

        return new InfoUsuarioDTO(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getPerfil()
        );

    }

    @Override
    public void eliminarUsuario(InfoUsuarioDTO infoUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNombre(infoUsuario.getNombre_us());
        usuarioDAO.eliminarPorNombre(usuario.getNombre());

    }
}
