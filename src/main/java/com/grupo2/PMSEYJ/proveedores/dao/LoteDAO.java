package com.grupo2.PMSEYJ.proveedores.dao;

import com.grupo2.PMSEYJ.clientes.model.ClienteNatural;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.Lote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {

    // ===============================
    // INSERTAR LOTE
    // ===============================
    public void insertar(Lote lote) {
        String sql = "INSERT INTO lote (codigo_barras, num_lote, stock, fecha_vn, " +
                "precio_compra, rentabilidad, tamano_caja, estado, tiene_iva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_lote";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lote.getCodigo_barras());
            ps.setString(2, lote.getNum_lote());
            ps.setInt(3, lote.getStock());
            ps.setDate(4, Date.valueOf(lote.getFecha_vn()));
            ps.setDouble(5, lote.getPrecio_compra());
            ps.setInt(6, lote.getRentabilidad());
            ps.setInt(7, lote.getTamano_caja());
            ps.setString(8, lote.getEstado());
            ps.setBoolean(9, lote.getTiene_iva());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lote.setId_lote(rs.getInt("id_lote")); // aquí asignas el ID generado
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar lote", e);
        }
    }


    // ===============================
    // CONSULTAR LOTES POR CÓDIGO DE BARRAS
    // ===============================
    public List<Lote> consultarPorCodigoBarras(String codigoBarras) {
        String sql = "SELECT * FROM lote WHERE codigo_barras = ?";
        List<Lote> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigoBarras);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearLote(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar lotes por código de barras", e);
        }

        return lista;
    }

    public Lote consultarPorId(int id_cn) {
        String sql = "SELECT * FROM lote WHERE id_lote = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearLote(rs); // aquí retornas el objeto mapeado
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar lote por ID", e);
        }

        return null; // si no encuentra nada
    }

    // ===============================
    // MÉTODO PRIVADO DE MAPEADO
    // ===============================
    private Lote mapearLote(ResultSet rs) throws SQLException {
        Lote lote = new Lote();
        lote.setId_lote(rs.getInt("id_lote"));
        lote.setCodigo_barras(rs.getString("codigo_barras"));
        lote.setNum_lote(rs.getString("num_lote"));
        lote.setStock(rs.getInt("stock"));
        lote.setFecha_vn(rs.getDate("fecha_vn").toLocalDate());
        lote.setPrecio_compra(rs.getDouble("precio_compra"));
        lote.setRentabilidad(rs.getInt("rentabilidad"));
        lote.setTamano_caja(rs.getInt("tamano_caja"));
        lote.setEstado(rs.getString("estado"));
        lote.setTiene_iva(rs.getBoolean("tiene_iva"));
        return lote;
    }
}

