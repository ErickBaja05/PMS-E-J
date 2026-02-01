package com.grupo2.PMSEYJ.proveedores.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.proveedores.model.Proveedor;


public class ProveedorDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setId_prove(rs.getInt("id_prove"));
        proveedor.setNombre_pro(rs.getString("nombre_pro"));
        proveedor.setTelefono_pro(rs.getString("telefono_pro"));
        proveedor.setCorreo_pro(rs.getString("correo_pro"));
        proveedor.setEstado_pv(rs.getString("estado_pv"));

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
    // CONSULTAR POR ID
    // ===============================
    public Proveedor consultarPorID(Integer id_prove) {
        String sql = "SELECT * FROM proveedores WHERE id_prove = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_prove);
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
    // INSERTAR PROVEEDOR
    // ===============================
    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre_pro,telefono_pro,correo_pro,estado_pv) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre_pro());
            ps.setString(2, proveedor.getTelefono_pro());
            ps.setString(3, proveedor.getCorreo_pro());
            ps.setString(4, proveedor.getEstado_pv());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar indice", e);
        }
    }

    public void actualizarCorreoPorNombre(String correo_pro, String nombre_pro) {
        String sql = "UPDATE proveedores SET correo_pro = ? WHERE nombre_pro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo_pro);
            ps.setString(2, nombre_pro);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el correo del proveedor", e);
        }
    }

    public void actualizarTelefonoPorNombre(String telefono_pro, String nombre_pro) {
        String sql = "UPDATE proveedores SET telefono_pro = ? WHERE nombre_pro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, telefono_pro);
            ps.setString(2, nombre_pro);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el telefono del proveedor", e);
        }
    }
    public void darDeAlta(String estado, String nombre_pro) {
        String sql = "UPDATE proveedores SET estado_pv = ? WHERE nombre_pro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setString(2, nombre_pro);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el telefono del proveedor", e);
        }
    }
    public void darDeBaja(String estado, String nombre_pro) {
        String sql = "UPDATE proveedores SET estado_pv = ? WHERE nombre_pro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setString(2, nombre_pro);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el telefono del proveedor", e);
        }
    }

    public void eliminarProveedor(String nombre_pro) {
        String sql = "DELETE FROM proveedores WHERE nombre_pro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre_pro);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el telefono del proveedor", e);
        }
    }
}

