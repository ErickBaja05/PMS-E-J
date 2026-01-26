package com.grupo2.PMSEYJ.inventarioYProductos.dao;
import com.grupo2.PMSEYJ.core.database.DatabaseConnection;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductoDAO {

    // ===============================
    // MÉTODO PRIVADO DE MAPEO
    // ===============================
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setCodigo_aux(rs.getString("codigo_ax"));
        p.setId_lab(rs.getInt("id_lab"));
        p.setCodigo_br(rs.getDouble("codigo_br"));
        p.setNombre_p(rs.getString("nombre_p"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setCategoria(rs.getString("categoria"));       // char(1) → String
        p.setForma_venta(rs.getString("forma_venta"));   // char(1) → String
        p.setTipo_venta(rs.getString("tipo_venta"));     // char(1) → String
        p.setPvp(rs.getDouble("pvp"));

        // Campos opcionales
        Date fechaUv = rs.getDate("fecha_uv");
        if (fechaUv != null) {
            p.setFecha_uv(fechaUv.toLocalDate());
        }

        p.setIndice_t(rs.getString("indice_t"));
        p.setPrincipio_ac(rs.getString("principio_ac"));
        p.setTiene_iva(rs.getBoolean("tiene_iva"));

        return p;
    }

    // ===============================
    // CONSULTAR TODOS LOS PRODUCTOS
    // ===============================
    public List<Producto> consultarTodos() {
        String sql = "SELECT * FROM producto";
        List<Producto> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los productos", e);
        }

        return lista;
    }

    // ===============================
    // CONSULTAR POR NOMBRE
    // ===============================
    public Producto consultarPorNombre(String nombre) {
        String sql = "SELECT * FROM producto WHERE nombre_p = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearProducto(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar producto por nombre", e);
        }

        return null;
    }

    public Producto consultarPorCodAux(String codAux) {
        String sql = "SELECT * FROM producto WHERE codigo_ax = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codAux);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearProducto(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar producto por nombre", e);
        }

        return null;
    }

    // ===============================
    // INSERTAR PRODUCTO (solo NOT NULL)
    // ===============================
    public void insertar(Producto p) {
        String sql = """
            INSERT INTO producto (
                codigo_ax, id_lab, codigo_br, nombre_p, descripcion,
                categoria, forma_venta, tipo_venta, pvp, indice_t
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCodigo_aux());
            ps.setInt(2, p.getId_lab());
            ps.setDouble(3, p.getCodigo_br());
            ps.setString(4, p.getNombre_p());
            ps.setString(5, p.getDescripcion());
            ps.setString(6, p.getCategoria());     // ya es String
            ps.setString(7, p.getForma_venta());   // ya es String
            ps.setString(8, p.getTipo_venta());    // ya es String
            ps.setDouble(9, p.getPvp());
            ps.setString(10, p.getIndice_t());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar producto", e);
        }
    }
}
