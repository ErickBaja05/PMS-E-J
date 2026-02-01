package com.grupo2.PMSEYJ.proveedores.dao;

import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.DetallePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    // ===============================
    // INSERTAR DETALLE
    // ===============================
    public void insertar(DetallePedido detalle) {
        String sql = "INSERT INTO detalle_pedido (id_pedido, codigo_barras, cantidad) VALUES (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getId_pedido());
            ps.setString(2, detalle.getCodigo_barras());
            ps.setInt(3, detalle.getCantidad());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar detalle de pedido", e);
        }
    }

    // ===============================
    // CONSULTAR TODOS POR ID_PEDIDO
    // ===============================
    public List<DetallePedido> consultarPorIdPedido(int idPedido) {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT id_pedido, codigo_barras, cantidad FROM detalle_pedido WHERE id_pedido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(mapearDetalle(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar detalles por id_pedido", e);
        }
        return detalles;
    }

    // ===============================
    // ELIMINAR POR ID_PEDIDO Y CODIGO_BARRAS
    // ===============================
    public boolean eliminar(int idPedido, String codigoBarras) {
        String sql = "DELETE FROM detalle_pedido WHERE id_pedido = ? AND codigo_barras = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.setString(2, codigoBarras);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle de pedido", e);
        }
    }

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private DetallePedido mapearDetalle(ResultSet rs) throws SQLException {
        DetallePedido detalle = new DetallePedido();
        detalle.setId_pedido(rs.getInt("id_pedido"));
        detalle.setCodigo_barras(rs.getString("codigo_barras"));
        detalle.setCantidad(rs.getInt("cantidad"));
        return detalle;
    }

    public DetallePedido consultarPorIdYCodigoBarras(int idPedido, String codigoBarras) {
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido = ? AND codigo_barras = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.setString(2, codigoBarras);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearDetalle(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el detalle del pedido con el id y el código de barras", e);
        }

        return null;
    }

    public void actualizarCantidadPorIdyCodigoBarras(int cantidad ,int idPedido, String codigoBarras) {
        String sql = "UPDATE detalle_pedido SET cantidad =? WHERE id_pedido = ? AND codigo_barras = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, idPedido);
            ps.setString(3, codigoBarras);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cantidad del detalle del pedido", e);
        }

    }


}