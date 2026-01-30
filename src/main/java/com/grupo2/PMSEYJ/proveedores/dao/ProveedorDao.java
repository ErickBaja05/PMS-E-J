package com.grupo2.PMSEYJ.proveedores.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.inventarioYProductos.model.IndiceTerapeutico;
import com.grupo2.PMSEYJ.proveedores.model.Proveedor;


public class ProveedorDao {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setId_prove(rs.getInt("id_prove"));
        proveedor.setNombre_pro(rs.getString("nombre_pro"));
        proveedor.setTelefono_pro(rs.getString("telefono_pro"));
        proveedor.setCorreo_pro(rs.getString("correo_pro"));

        return proveedor;
    }

    // ===============================
    // CONSULTAR TODOS LOS PROVEEDORES
    // ===============================
    public List<Proveedor> consultarTodos() {
        String sql = "SELECT * FROM proveedores";
        List<Proveedor> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearProveedor(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los proveedores", e);
        }

        return lista;
    }

    // ===============================
    // CONSULTAR POR NOMBRE
    // ===============================
    public Proveedor consultarPorNombre(String nombre_pro) {
        String sql = "SELECT * FROM proveedores WHERE nombre_pro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre_pro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearProveedor(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar proveedor por nombre", e);
        }

        return null;
    }

    // ===============================
    // INSERTAR INDICE
    // ===============================
    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO indice_terapeutico (nombre_indice) VALUES (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre_pro());
            ps.setString(2, proveedor.getTelefono_pro());
            ps.setString(3, proveedor.getCorreo_pro());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar indice", e);
        }
    }
}

