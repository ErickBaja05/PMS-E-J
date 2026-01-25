package com.grupo2.PMSEYJ.clientes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.grupo2.PMSEYJ.clientes.model.ClienteJuridico;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;

public class ClienteJuridicoDAO {

    public void insertar(ClienteJuridico cliente) {
        String sql = """
                INSERT INTO cliente_juridico (ruc, razon_social, direccion_cj, correo_cj, telefono_cj, estado_cj)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getRuc());
            ps.setString(2, cliente.getRazon_social());
            ps.setString(3, cliente.getDireccion_cj());
            ps.setString(4, cliente.getCorreo_cj());
            ps.setString(5, cliente.getTelefono_cj());
            ps.setString(6, cliente.getEstado_cj());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cliente jurídico", e);
        }
    }

    // ===============================
// MÉTODO PRIVADO DE MAPEO
// ===============================
    private ClienteJuridico mapearClienteJuridico(ResultSet rs) throws SQLException {
        ClienteJuridico c = new ClienteJuridico();
        c.setId_cj(rs.getInt("id_cj"));
        c.setRuc(rs.getString("ruc"));
        c.setRazon_social(rs.getString("razon_social"));
        c.setDireccion_cj(rs.getString("direccion_cj"));
        c.setCorreo_cj(rs.getString("correo_cj"));
        c.setTelefono_cj(rs.getString("telefono_cj"));
        c.setEstado_cj(rs.getString("estado_cj"));
        return c;
    }

    public ClienteJuridico consultarPorId(int id_cj) {
        String sql = "SELECT * FROM cliente_juridico WHERE id_cj = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cj);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearClienteJuridico(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar cliente jurídico por ID", e);
        }

        return null;
    }

    public ClienteJuridico consultarPorRuc(String ruc) {
        String sql = "SELECT * FROM cliente_juridico WHERE ruc= ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ruc);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearClienteJuridico(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar cliente jurídico por RUC", e);
        }

        return null;
    }

    public void actualizarCorreoPorRUC(String ruc, String nuevoCorreo) {
        String sql = "UPDATE cliente_juridico SET correo_cj = ? WHERE ruc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoCorreo);
            ps.setString(2, ruc);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el correo del cliente juridico", e);
        }
    }
    public void actualizarDireccionPorRUC(String ruc, String nuevaDireccion) {
        String sql = "UPDATE cliente_juridico SET direccion_cj = ? WHERE ruc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevaDireccion);
            ps.setString(2, ruc);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la dirección del cliente juridico", e);
        }
    }
    public void actualizarTelefonoPorRUC(String ruc, String nuevoTelefono) {
        String sql = "UPDATE cliente_juridico SET telefono_cj = ? WHERE ruc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoTelefono);
            ps.setString(2, ruc);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el teléfono del cliente juridico", e);
        }
    }

    public void darDeBaja(String RUC) {
        String sql = "UPDATE cliente_juridico SET estado_cj = ? WHERE ruc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "I");
            ps.setString(2, RUC);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al dar de baja al cliente juridico", e);
        }
    }

    public void darDeAlta(String RUC) {
        String sql = "UPDATE cliente_juridico SET estado_cj = ? WHERE ruc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "A");
            ps.setString(2, RUC);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el teléfono del cliente natural", e);
        }
    }

    public void eliminar(int id_cj) {
        String sql = "DELETE FROM cliente_juridico WHERE id_cj = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cj);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente jurídico", e);
        }
    }
}
