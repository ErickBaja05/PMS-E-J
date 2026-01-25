package com.grupo2.PMSEYJ.administracion.gestionParametros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grupo2.PMSEYJ.administracion.gestionParametros.model.Firma;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;


public class FirmaDAO {

    // ===============================
    // INSERT
    // ===============================
    public void insertar(Firma firma) {
        String sql = """
                INSERT INTO firma (ruta_fr)
                VALUES (?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firma.getRuta_fr());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la firma", e);
        }
    }






}
