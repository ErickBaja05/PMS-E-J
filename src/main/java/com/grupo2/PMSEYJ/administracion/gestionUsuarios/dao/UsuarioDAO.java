package com.grupo2.PMSEYJ.administracion.gestionUsuarios.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;

public class UsuarioDAO {

    // ===============================
    // INSERT
    // ===============================
    public void insertar(Usuario usuario) {
        String sql = """
                INSERT INTO usuarios (nombre_us, correo_us, password_us, perfil_us)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getPerfil());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }

    // ===============================
    // SELECT BY ID
    // ===============================
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por id", e);
        }

        return null;
    }

    // ===============================
    // SELECT ALL
    // ===============================
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar usuarios", e);
        }

        return lista;
    }

    // ===============================
    // UPDATE
    // ===============================
    public void actualizar(Usuario usuario) {
        String sql = """
                UPDATE usuarios
                SET nombre_us = ?, correo_us = ?, password_us = ?, perfil_us = ?
                WHERE id_usuario = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getPerfil());
            ps.setInt(5, usuario.getIdUsuario());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    // ===============================
    // DELETE
    // ===============================
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre_us"));
        u.setCorreo(rs.getString("correo_us"));
        u.setPassword(rs.getString("password_us"));
        u.setPerfil(rs.getString("perfil_us"));
        return u;
    }

    public Usuario buscarPorNombre(String nombre_us) {
        String sql = "SELECT * FROM usuarios WHERE nombre_us = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre_us);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por nombre", e);
        }

        return null;
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE correo_us = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por nombre", e);
        }

        return null;
    }

}
