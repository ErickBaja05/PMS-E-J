package com.grupo2.PMSEYJ.administracion.gestionParametros.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupo2.PMSEYJ.administracion.gestionParametros.model.MetodoPago;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;

public class MetodoPagoDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private MetodoPago mapearMetodoPago(ResultSet rs) throws SQLException {
        MetodoPago mp = new MetodoPago();
        mp.setId_pago(rs.getInt("id_pago"));
        mp.setNombre_pago(rs.getString("nombre_pago"));
        mp.setEstado_pago(rs.getString("estado_pago"));
        return mp;
    }

    // ===============================
    // INSERTAR UN MÉTODO DE PAGO
    // ===============================
    public void insertar(MetodoPago metodo) {
        String sql = "INSERT INTO metodo_pago (nombre_pago, estado_pago) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, metodo.getNombre_pago());
            ps.setString(2, metodo.getEstado_pago());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar método de pago", e);
        }
    }

    // ===============================
    // ACTUALIZAR ESTADO POR NOMBRE
    // ===============================
    public void actualizarEstadoPorNombre(String nombrePago, String nuevoEstado) {
        String sql = "UPDATE metodo_pago SET estado_pago = ? WHERE nombre_pago = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setString(2, nombrePago);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar estado del método de pago", e);
        }
    }

    // ===============================
    // ELIMINAR MÉTODO POR NOMBRE
    // ===============================
    public void eliminarPorNombre(String nombrePago) {
        String sql = "DELETE FROM metodo_pago WHERE nombre_pago = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombrePago);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar método de pago", e);
        }
    }

    // ===============================
    // RECUPERAR TODOS LOS MÉTODOS
    // ===============================
    public List<MetodoPago> listarTodos() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pago ORDER BY id_pago";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMetodoPago(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar métodos de pago", e);
        }

        return lista;
    }

    // ===============================
    // CONSULTAR UN MÉTODO POR NOMBRE
    // ===============================
    public MetodoPago buscarPorNombre(String nombrePago) {
        String sql = "SELECT * FROM metodo_pago WHERE nombre_pago = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombrePago);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearMetodoPago(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar método de pago por nombre", e);
        }

        return null;
    }
}
