package com.grupo2.PMSEYJ.clientes.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.clientes.model.ClienteNatural;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;

public class ClienteNaturalDAO {

    public void insertar(ClienteNatural cliente) {
        String sql = """
                INSERT INTO cliente_natural (cedula_cn, nombre_cn, direccion_cn, correo_cn, telefono_cn, fecha_cn, estado_cn)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula_cn());
            ps.setString(2, cliente.getNombre_cn());
            ps.setString(3, cliente.getDireccion_cn());
            ps.setString(4, cliente.getCorreo_cn());
            ps.setString(5, cliente.getTelefono_cn());
            ps.setDate(6, Date.valueOf(cliente.getFecha_cn()));
            ps.setString(7, cliente.getEstado_cn());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cliente natural", e);
        }
    }

    public ClienteNatural consultarPorId(int id_cn) {
        String sql = "SELECT * FROM cliente_natural WHERE id_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                mapearClienteNatural(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar cliente natural por ID", e);
        }

        return null;
    }

    // ===============================
// MÉTODO PRIVADO DE MAPEO
// ===============================
    private ClienteNatural mapearClienteNatural(ResultSet rs) throws SQLException {
        ClienteNatural c = new ClienteNatural();
        c.setId_cn(rs.getInt("id_cn"));
        c.setCedula_cn(rs.getString("cedula_cn"));
        c.setNombre_cn(rs.getString("nombre_cn"));
        c.setDireccion_cn(rs.getString("direccion_cn"));
        c.setCorreo_cn(rs.getString("correo_cn"));
        c.setTelefono_cn(rs.getString("telefono_cn"));
        c.setFecha_cn(rs.getDate("fecha_cn").toLocalDate());
        c.setEstado_cn(rs.getString("estado_cn"));
        return c;
    }

    public ClienteNatural consultarPorCedula(String  cedula_cn) {
        String sql = "SELECT * FROM cliente_natural WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula_cn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapearClienteNatural(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar cliente natural por cédula", e);
        }

        return null;
    }

    public void actualizarCorreoPorCedula(String cedula_cn, String nuevoCorreo) {
        String sql = "UPDATE cliente_natural SET correo_cn = ? WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoCorreo);
            ps.setString(2, cedula_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el correo del cliente natural", e);
        }
    }
    public void actualizarDireccionPorCedula(String cedula_cn, String nuevaDireccion) {
        String sql = "UPDATE cliente_natural SET direccion_cn = ? WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevaDireccion);
            ps.setString(2, cedula_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la dirección del cliente natural", e);
        }
    }
    public void actualizarTelefonoPorCedula(String cedula_cn, String nuevoTelefono) {
        String sql = "UPDATE cliente_natural SET telefono_cn = ? WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoTelefono);
            ps.setString(2, cedula_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el teléfono del cliente natural", e);
        }
    }
    public void darDeBaja(String cedula_cn) {
        String sql = "UPDATE cliente_natural SET estado_cn = ? WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "I");
            ps.setString(2, cedula_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el teléfono del cliente natural", e);
        }
    }

    public void darDeAlta(String cedula_cn) {
        String sql = "UPDATE cliente_natural SET estado_cn = ? WHERE cedula_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "A");
            ps.setString(2, cedula_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el teléfono del cliente natural", e);
        }
    }

    public void eliminar(int id_cn) {
        String sql = "DELETE FROM cliente_natural WHERE id_cn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente natural", e);
        }
    }
}

