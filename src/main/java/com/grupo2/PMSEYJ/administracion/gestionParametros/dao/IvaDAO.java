package com.grupo2.PMSEYJ.administracion.gestionParametros.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.IVA;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;

public class IvaDAO {

    // ===============================
    // INSERT
    // ===============================
    public void insertar(IVA iva) {
        String sql = """
                INSERT INTO iva (iva_valor)
                VALUES (?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, iva.getValorNuevo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la firma", e);
        }
    }

    // ===============================
    // UPDATE
    // ===============================
    public void actualizar(IVA iva) {
        String sql = """
                UPDATE  iva  SET iva_valor = ? WHERE iva_valor = ?;
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, iva.getValorNuevo());
            ps.setDouble(2, iva.getValorActual());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la firma", e);
        }
    }


    public IVA consultarValor() {
        String sql = "SELECT * FROM iva";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new IVA(rs.getDouble("iva_valor"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el valor del iva", e);
        }

        return null;
    }
}
