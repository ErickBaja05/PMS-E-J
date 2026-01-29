package com.grupo2.PMSEYJ.inventarioYProductos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.inventarioYProductos.model.IndiceTerapeutico;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Laboratorio;

public class IndiceTerapeuticoDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private IndiceTerapeutico mapearIndiceTerapeutico(ResultSet rs) throws SQLException {
        IndiceTerapeutico indice = new IndiceTerapeutico();
        indice.setId_indice_terapeutico(rs.getInt("id_indice_terapeutico"));
        indice.setNombre_indice(rs.getString("nombre_indice"));
        return indice;
    }

    // ===============================
    // CONSULTAR TODOS LOS LABORATORIOS
    // ===============================
    public List<IndiceTerapeutico> consultarTodos() {
        String sql = "SELECT * FROM indice_terapeutico";
        List<IndiceTerapeutico> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearIndiceTerapeutico(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los laboratorios", e);
        }

        return lista;
    }

    // ===============================
    // CONSULTAR POR NOMBRE
    // ===============================
    public IndiceTerapeutico consultarPorNombre(String nombre_indice) {
        String sql = "SELECT * FROM indice_terapeutico WHERE nombre_indice = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre_indice);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearIndiceTerapeutico(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar indice  por nombre", e);
        }

        return null;
    }

    // ===============================
    // INSERTAR INDICE
    // ===============================
    public void insertar(IndiceTerapeutico indice) {
        String sql = "INSERT INTO indice_terapeutico (nombre_indice) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, indice.getNombre_indice());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar indice", e);
        }
    }
}
