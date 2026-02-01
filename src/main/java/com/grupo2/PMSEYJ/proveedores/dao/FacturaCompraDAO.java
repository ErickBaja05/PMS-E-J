package com.grupo2.PMSEYJ.proveedores.dao;

import java.sql.*;

import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.FacturaCompra;


public class FacturaCompraDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private FacturaCompra mapearFacturaCompra(ResultSet rs) throws SQLException {
        FacturaCompra facturaCompra = new FacturaCompra();
        facturaCompra.setId_fc(rs.getInt("id_fc"));
        facturaCompra.setNum_fc(rs.getString("num_fc"));
        facturaCompra.setEstado(rs.getString("estado"));
        facturaCompra.setFecha_fc(rs.getDate("fecha_fc").toLocalDate());
        facturaCompra.setFue_ingresada(rs.getBoolean("fue_ingresada"));
        facturaCompra.setId_prove(rs.getInt("id_prove"));
        return facturaCompra;
    }



    // ===============================
    // CONSULTAR POR NOMBRE
    // ===============================
    public FacturaCompra consultarPorNumero(String num_fc) {
        String sql = "SELECT * FROM factura_c WHERE num_fc = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, num_fc);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearFacturaCompra(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la factura por nombre", e);
        }

        return null;
    }

    public FacturaCompra consultarPorNumeroYProveedor(String num_fc, Integer id_prove) {
        String sql = "SELECT * FROM factura_c WHERE num_fc = ? AND id_prove = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, num_fc);
            ps.setInt(2, id_prove);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearFacturaCompra(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la factura por nombre y proveedor", e);
        }

        return null;
    }

    // ===============================
    // INSERTAR FACTURA
    // ===============================
    public void insertar(FacturaCompra facturaCompra) {
        String sql = "INSERT INTO factura_c (num_fc, estado, fecha_fc, fue_ingresada, id_prove) " +
                "VALUES (?,?,?,?,?) RETURNING id_fc";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, facturaCompra.getNum_fc());
            ps.setString(2, facturaCompra.getEstado());
            ps.setDate(3, Date.valueOf(facturaCompra.getFecha_fc()));
            ps.setBoolean(4, facturaCompra.getFue_ingresada());
            ps.setInt(5, facturaCompra.getId_prove());

            // Aquí usamos executeQuery porque RETURNING devuelve un ResultSet
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    facturaCompra.setId_fc(rs.getInt("id_fc")); // asignamos el ID generado
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar factura", e);
        }
    }

    public void actualizarEstadoPorNumero(String estado, String num_fc) {
        String sql = "UPDATE factura_c SET estado = ? WHERE num_fc = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setString(2, num_fc);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de la factura", e);
        }
    }

    public void ingresarAlSistemaPorNumeroYProveedor(Boolean estado, String num_fc, Integer id_prove) {
        String sql = "UPDATE factura_c SET fue_ingresada = ? WHERE num_fc = ? AND id_prove = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, estado);
            ps.setString(2, num_fc);
            ps.setInt(3, id_prove);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de la factura", e);
        }
    }



}