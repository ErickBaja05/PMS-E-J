package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class cotejarFacturaController {

    // --- ELEMENTOS FXML (Sincronizados con el XML) ---
    @FXML private TextField txtNumFactura;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCantidad;

    @FXML private TableView<ProductoDetalle> tvDetalleFactura;
    @FXML private TableColumn<ProductoDetalle, String> colProducto;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidad;

    // Lista observable para manejar los datos de la tabla
    private ObservableList<ProductoDetalle> listaProductos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla para que reconozcan los atributos del modelo
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tvDetalleFactura.setItems(listaProductos);
    }

    // --- MÉTODOS DE APOYO ---

    private void mostrarMensaje(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    // --- ACCIONES ---

    @FXML
    void buscarFactura(ActionEvent event) {
        String numFactura = txtNumFactura.getText().trim();

        if (numFactura.isEmpty()) {
            mostrarMensaje("Error", "Debe ingresar un número de factura.", Alert.AlertType.WARNING);
            return;
        }

        // Simulación de búsqueda
        if (numFactura.equals("F-001")) {
            mostrarMensaje("Éxito", "Factura encontrada. Puede proceder a agregar productos.", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("No Encontrado", "No existe una factura con el número ingresado", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String cantStr = txtCantidad.getText().trim();

        // Validaciones de entrada
        if (nombre.isEmpty() || cantStr.isEmpty()) {
            mostrarMensaje("Error", "Debe completar el nombre y la cantidad del producto.", Alert.AlertType.ERROR);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantStr);
            if (cantidad <= 0) throw new NumberFormatException();

            // Agregar a la lista de la tabla
            listaProductos.add(new ProductoDetalle(nombre, cantidad));

            // Limpiar campos de entrada de producto
            txtNombre.clear();
            txtCantidad.clear();
            txtNombre.requestFocus();

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "La cantidad debe ser un número entero mayor a 0", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleCotejarFactura(ActionEvent event) {
        if (listaProductos.isEmpty()) {
            mostrarMensaje("Error", "No hay productos en la tabla para cotejar.", Alert.AlertType.WARNING);
            return;
        }

        // Simulación de finalización
        mostrarMensaje("Éxito", "Cotejo de factura finalizado y guardado correctamente.", Alert.AlertType.INFORMATION);
        limpiarTodo();
    }

    private void limpiarTodo() {
        txtNumFactura.clear();
        txtNombre.clear();
        txtCantidad.clear();
        listaProductos.clear();
    }

    // --- CLASE MODELO INTERNA (Para la tabla) ---
    public static class ProductoDetalle {
        private String nombre;
        private int cantidad;

        public ProductoDetalle(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        public String getNombre() { return nombre; }
        public int getCantidad() { return cantidad; }
    }
}