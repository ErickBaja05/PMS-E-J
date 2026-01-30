package com.grupo2.PMSEYJ.proveedores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.Cotejo;
import com.grupo2.PMSEYJ.proveedores.model.Proveedor;


public class CotejoDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Cotejo mapearCotejo(ResultSet rs) throws SQLException {
        Cotejo cotejo = new Cotejo();
        cotejo.setId_ct(rs.getInt("id_ct"));
        cotejo.setId_fc(rs.getInt("id_fc"));
        cotejo.setId_lote(rs.getInt("id_lote"));
        cotejo.setCantidad(rs.getInt("cantidad"));
        return cotejo;
    }

    // ===============================
    // CONSULTAR TODOS LOS DETALLES DE UN COTEJO
    // ===============================
    public List<Cotejo> consultarPorFactura(Integer idFc) {
        String sql = "SELECT * FROM cotejo WHERE id_factura = ?";
        List<Cotejo> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFc); // asignamos el parámetro idFc
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCotejo(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los cotejos por factura", e);
        }

        return lista;
    }


    // ===============================
    // INSERTAR COTEJO
    // ===============================
    public void insertar(Cotejo cotejo) {
        String sql = "INSERT INTO cotejo (id_fc,id_lote,cantidad) VALUES (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cotejo.getId_fc() );
            ps.setInt(2, cotejo.getId_lote() );
            ps.setInt(3, cotejo.getCantidad());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cotejo", e);
        }
    }



    public void actualizarCantidadPorLoteYFactura(Integer cantidad, Integer id_fc, Integer id_lote) {
        String sql = "UPDATE cotejo SET cantidad = ? WHERE id_fc = ? AND id_lote = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, id_fc);
            ps.setInt(3, id_lote);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cantidad", e);
        }
    }

    public void eliminarProducto(Integer id_fc, Integer id_lote) {
        String sql = "DELETE FROM cotejo WHERE id_fc = ? AND id_lote = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_fc);
            ps.setInt(2, id_lote);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }
}
