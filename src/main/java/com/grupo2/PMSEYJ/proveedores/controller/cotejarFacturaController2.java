package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.proveedores.model.ProductoDetalle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cotejarFacturaController2 {

    // =========================
    // CONTROLES FXML
    // =========================
    @FXML private TextField txtNumFactura;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCantidad;

    @FXML private Button btnAgregarItem;
    @FXML private Button btnCancelarEdicion;

    // TABLA IZQUIERDA (FACTURA ORIGINAL)
    @FXML private TableView<ProductoDetalle> tvFacturaOriginal;
    @FXML private TableColumn<ProductoDetalle, String> colProductoOrig;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidadOrig;

    // TABLA DERECHA (MERCANCÍA FÍSICA)
    @FXML private TableView<ProductoDetalle> tvDetalleFactura;
    @FXML private TableColumn<ProductoDetalle, String> colProducto;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidad;

    // =========================
    // LISTAS Y ESTADO
    // =========================
    private final ObservableList<ProductoDetalle> listaCotejo =
            FXCollections.observableArrayList();

    private final ObservableList<ProductoDetalle> listaOriginalDisplay =
            FXCollections.observableArrayList();

    private final List<ProductoDetalle> facturaOriginal = new ArrayList<>();

    private String estadoFactura = "";
    private ProductoDetalle productoEnEdicion = null;

    // =========================
    // INITIALIZE
    // =========================
    @FXML
    public void initialize() {

        // TABLA IZQUIERDA
        colProductoOrig.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCantidadOrig.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());
        tvFacturaOriginal.setItems(listaOriginalDisplay);

        // TABLA DERECHA
        colProducto.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCantidad.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());
        tvDetalleFactura.setItems(listaCotejo);

        tvFacturaOriginal.setPlaceholder(new Label("Busque una factura para ver el detalle"));
        tvDetalleFactura.setPlaceholder(new Label("No hay productos contados aún"));

        // 🔴 PRUEBA VISUAL
        listaOriginalDisplay.add(new ProductoDetalle("PRUEBA", 10));
    }


    // =========================
    // BUSCAR FACTURA
    // =========================
    @FXML
    void buscarFactura(ActionEvent event) {

        String numFactura = txtNumFactura.getText().trim();

        if (numFactura.isEmpty()) {
            mostrarMensaje(
                    "Error",
                    "Ingrese un número de factura.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // SIMULACIÓN DE BD
        if (numFactura.equals("F-001")) {

            estadoFactura = "NO COTEJADA";
            limpiarListas();
            cancelarEdicion(null);

            facturaOriginal.clear();
            facturaOriginal.add(new ProductoDetalle("Paracetamol", 10));
            facturaOriginal.add(new ProductoDetalle("Ibuprofeno", 5));
            facturaOriginal.add(new ProductoDetalle("Amoxicilina", 20));

            listaOriginalDisplay.setAll(facturaOriginal);

            mostrarMensaje(
                    "Éxito",
                    "Factura cargada. Ingrese la mercancía física.",
                    Alert.AlertType.INFORMATION
            );
        } else {
            mostrarMensaje(
                    "Error",
                    "Factura no encontrada.",
                    Alert.AlertType.ERROR
            );
        }
    }

    // =========================
    // AGREGAR / EDITAR PRODUCTO
    // =========================
    @FXML
    void handleAgregarProducto(ActionEvent event) {

        if (estadoFactura.isEmpty()) {
            mostrarMensaje(
                    "Error",
                    "Primero debe buscar una factura.",
                    Alert.AlertType.ERROR
            );
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            if (nombre.isEmpty()) {
                mostrarMensaje(
                        "Error",
                        "El nombre del producto no puede estar vacío.",
                        Alert.AlertType.WARNING
                );
                return;
            }

            if (productoEnEdicion == null) {
                // AGREGAR
                listaCotejo.add(new ProductoDetalle(nombre, cantidad));
            } else {
                // ACTUALIZAR
                productoEnEdicion.setNombre(nombre);
                productoEnEdicion.setCantidad(cantidad);
                cancelarEdicion(null);
            }

            txtNombre.clear();
            txtCantidad.clear();

        } catch (NumberFormatException e) {
            mostrarMensaje(
                    "Error",
                    "Cantidad inválida.",
                    Alert.AlertType.ERROR
            );
        }
    }

    // =========================
    // SELECCIONAR PARA EDITAR
    // =========================
    @FXML
    void seleccionarProductoParaEditar(MouseEvent event) {

        ProductoDetalle seleccion =
                tvDetalleFactura.getSelectionModel().getSelectedItem();

        if (seleccion != null) {

            productoEnEdicion = seleccion;
            txtNombre.setText(seleccion.getNombre());
            txtCantidad.setText(String.valueOf(seleccion.getCantidad()));

            btnAgregarItem.setText("ACTUALIZAR");
            btnCancelarEdicion.setDisable(false);
        }
    }

    // =========================
    // CANCELAR EDICIÓN
    // =========================
    @FXML
    void cancelarEdicion(ActionEvent event) {

        productoEnEdicion = null;
        txtNombre.clear();
        txtCantidad.clear();
        tvDetalleFactura.getSelectionModel().clearSelection();

        btnAgregarItem.setText("AGREGAR");
        btnCancelarEdicion.setDisable(true);
    }

    // =========================
    // FINALIZAR COTEJO
    // =========================
    @FXML
    void handleCotejarFactura(ActionEvent event) throws IOException {

        if (listaCotejo.isEmpty()) {
            mostrarMensaje(
                    "Error",
                    "No hay productos ingresados para cotejar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        boolean coinciden = compararListas();

        if (coinciden) {
            estadoFactura = "COTEJADA";
            mostrarMensaje(
                    "Resultado",
                    "La mercancía recibida coincide con la factura.",
                    Alert.AlertType.INFORMATION
            );
        } else {
            estadoFactura = "COTEJADA CON DIFERENCIAS";
            mostrarMensaje(
                    "Resultado",
                    "Existen diferencias entre la factura y la mercancía recibida.",
                    Alert.AlertType.WARNING
            );
        }

        // Navegación (si existe la vista)
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/proveedores/fxml/resultadoCotejo.fxml")
            );
            Parent root = loader.load();
            NavigationUtil.openNewWindow(event, root, "Resultado Cotejo");
        } catch (Exception e) {
            // Si no existe la vista, no rompe la app
            System.err.println("Vista de resultado no disponible");
        }

        limpiarTodo();
    }

    // =========================
    // COMPARAR LISTAS
    // =========================
    private boolean compararListas() {

        if (listaCotejo.size() != facturaOriginal.size()) {
            return false;
        }

        for (ProductoDetalle original : facturaOriginal) {
            boolean encontrado = listaCotejo.stream().anyMatch(recibido ->
                    recibido.getNombre().equalsIgnoreCase(original.getNombre())
                            && recibido.getCantidad() == original.getCantidad()
            );

            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================
    private void limpiarListas() {
        listaCotejo.clear();
        listaOriginalDisplay.clear();
    }

    private void limpiarTodo() {
        txtNumFactura.clear();
        txtNombre.clear();
        txtCantidad.clear();
        facturaOriginal.clear();
        estadoFactura = "";
        cancelarEdicion(null);
    }

    private void mostrarMensaje(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }
}
