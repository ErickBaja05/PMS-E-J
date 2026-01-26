package com.grupo2.PMSEYJ.inventarioYProductos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Laboratorio;

public class LaboratorioDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Laboratorio mapearLaboratorio(ResultSet rs) throws SQLException {
        Laboratorio l = new Laboratorio();
        l.setId_lab(rs.getInt("id_lab"));
        l.setNombre_lab(rs.getString("nombre_lab"));
        return l;
    }

    // ===============================
    // CONSULTAR TODOS LOS LABORATORIOS
    // ===============================
    public List<Laboratorio> consultarTodos() {
        String sql = "SELECT * FROM laboratorio";
        List<Laboratorio> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearLaboratorio(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los laboratorios", e);
        }

        return lista;
    }

    // ===============================
    // CONSULTAR POR NOMBRE
    // ===============================
    public Laboratorio consultarPorNombre(String nombre_lab) {
        String sql = "SELECT * FROM laboratorio WHERE nombre_lab = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre_lab);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearLaboratorio(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar laboratorio por nombre", e);
        }

        return null;
    }

    // ===============================
    // INSERTAR LABORATORIO
    // ===============================
    public void insertar(Laboratorio l) {
        String sql = "INSERT INTO laboratorio (nombre_lab) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, l.getNombre_lab());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar laboratorio", e);
        }
    }
}
