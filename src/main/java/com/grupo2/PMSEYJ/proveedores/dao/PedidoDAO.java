package com.grupo2.PMSEYJ.proveedores.dao;

import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.Pedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // ===============================
    // INSERTAR PEDIDO
    // ===============================
    public void insertar(Pedido pedido) {
        String sql = "INSERT INTO pedido_pv (estado, fecha_pv) VALUES (?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, pedido.getEstado());
            ps.setDate(2, Date.valueOf(pedido.getFecha_pv()));
            ps.executeUpdate();

            // Recuperar el id autogenerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId_pedido(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar pedido", e);
        }
    }

    public void definirProveedorAlPedido(int id_pedido, int id_prove) {
        String sql = "UPDATE pedido_pv SET id_prove=? WHERE id_pedido=? ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, id_prove);
            ps.setInt(2, id_pedido);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error al definir el proveedor al pedido", e);
        }
    }

    public void definirFechaEnvio(int id_pedido, LocalDate fecha_envio) {
        String sql = "UPDATE pedido_pv SET fecha_pv=? WHERE id_pedido=? ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setDate(1, Date.valueOf(fecha_envio));
            ps.setInt(2, id_pedido);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error al definir el proveedor al pedido", e);
        }
    }

    public void enviarPedido(int id_pedido, String estado) {
        String sql = "UPDATE pedido_pv SET estado=? WHERE id_pedido=? ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){


            ps.setString(1, estado);
            ps.setInt(2, id_pedido);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error al definir el proveedor al pedido", e);
        }
    }

    // ===============================
    // CONSULTAR TODOS
    // ===============================
    public List<Pedido> consultarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id_pedido, id_prove, estado, fecha_pv FROM pedido_pv";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pedidos.add(mapearPedido(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar pedidos", e);
        }
        return pedidos;
    }

    // ===============================
    // CONSULTAR POR ID
    // ===============================
    public Pedido consultarPorId(int id) {
        String sql = "SELECT id_pedido, id_prove, estado, fecha_pv FROM pedido_pv WHERE id_pedido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar pedido por id", e);
        }
        return null;
    }

    public Pedido consultarPorEstado(String estado) {
        String sql = "SELECT * FROM pedido_pv WHERE estado = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar pedido por id", e);
        }
        return null;
    }

    // ===============================
    // ELIMINAR POR ID
    // ===============================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM pedido_pv WHERE id_pedido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pedido", e);
        }
    }



    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(rs.getInt("id_pedido"));
        pedido.setId_prove(rs.getInt("id_prove"));
        pedido.setEstado(rs.getString("estado"));
        pedido.setFecha_pv(rs.getDate("fecha_pv").toLocalDate());
        return pedido;
    }
}
