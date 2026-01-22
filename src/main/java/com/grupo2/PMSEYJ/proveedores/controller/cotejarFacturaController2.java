package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.proveedores.model.ProductoDetalle;
import com.grupo2.PMSEYJ.proveedores.model.ResultadoItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
    @FXML private Button btnCotejarFactura;

    @FXML private TableView<ProductoDetalle> tvFacturaOriginal;
    @FXML private TableColumn<ProductoDetalle, String> colProductoOrig;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidadOrig;

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

    private String estadoFactura = null;
    private ProductoDetalle productoEnEdicion = null;

    // =========================
    // INITIALIZE
    // =========================
    @FXML
    public void initialize() {

        colProductoOrig.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCantidadOrig.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());
        tvFacturaOriginal.setItems(listaOriginalDisplay);

        colProducto.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCantidad.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());
        tvDetalleFactura.setItems(listaCotejo);

        tvFacturaOriginal.setPlaceholder(new Label("Busque una factura para ver el detalle"));
        tvDetalleFactura.setPlaceholder(new Label("Ingrese la cantidad real de los productos"));

        bloquearIngresoProductos();
    }

    // =========================
    // CONTROL DE ESTADO UI
    // =========================
    private void bloquearIngresoProductos() {
        txtNombre.setDisable(true);
        txtCantidad.setDisable(true);
        btnAgregarItem.setDisable(true);
        btnCancelarEdicion.setDisable(true);
        tvDetalleFactura.setDisable(true);
        btnCotejarFactura.setDisable(true);
    }

    private void habilitarIngresoProductos() {
        txtNombre.setDisable(false);
        txtCantidad.setDisable(false);
        btnAgregarItem.setDisable(false);
        tvDetalleFactura.setDisable(false);
        btnCotejarFactura.setDisable(false);
    }

    // =========================
    // BUSCAR FACTURA
    // =========================
    @FXML
    void buscarFactura(ActionEvent event) {

        String numFactura = txtNumFactura.getText().trim();

        if (numFactura.isEmpty()) {
            mostrarMensaje("Error", "Ingrese el número de la factura.", Alert.AlertType.WARNING);
            return;
        }

        // SIMULACIÓN BD
        if (!"101".equals(numFactura)) {
            mostrarMensaje("Error", "Factura no registrada", Alert.AlertType.ERROR);
            limpiarTodo();
            return;
        }

        if ("COTEJADA".equals(estadoFactura) ||
                "COTEJADA CON DIFERENCIAS".equals(estadoFactura)) {

            mostrarMensaje("Advertencia",
                    "La factura ya fue cotejada",
                    Alert.AlertType.WARNING);
            return;
        }

        // FACTURA VÁLIDA
        estadoFactura = "NO COTEJADA";
        limpiarListas();
        cancelarEdicion(null);

        facturaOriginal.clear();
        facturaOriginal.add(new ProductoDetalle("Paracetamol", 10));
        facturaOriginal.add(new ProductoDetalle("Ibuprofeno", 5));
        facturaOriginal.add(new ProductoDetalle("Amoxicilina", 20));

        listaOriginalDisplay.setAll(facturaOriginal);

        habilitarIngresoProductos();

        mostrarMensaje("Éxito",
                "Factura cargada. Ingrese la cantidad real de los productos.",
                Alert.AlertType.INFORMATION);
    }

    // =========================
    // AGREGAR / EDITAR PRODUCTO
    // =========================
    @FXML
    void handleAgregarProducto(ActionEvent event) {

        String nombre = txtNombre.getText().trim();
        String cantidadTxt = txtCantidad.getText().trim();

        if (nombre.isEmpty() && cantidadTxt.isEmpty()) {
            mostrarMensaje("Error",
                    "Debe ingresar el nombre del producto y la cantidad real.",
                    Alert.AlertType.WARNING);
            return;
        }

        if (nombre.isEmpty()) {
            mostrarMensaje("Error",
                    "Debe ingresar el nombre del producto.",
                    Alert.AlertType.WARNING);
            return;
        }

        if (cantidadTxt.isEmpty()) {
            mostrarMensaje("Error",
                    "Debe ingresar la cantidad real del producto.",
                    Alert.AlertType.WARNING);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTxt);
        } catch (NumberFormatException e) {
            mostrarMensaje("Error",
                    "Cantidad no válida, ingrese un número entero mayor o igual a 0",
                    Alert.AlertType.ERROR);
            return;
        }

        if (cantidad < 0) {
            mostrarMensaje("Error",
                    "Cantidad no válida, ingrese un número entero mayor o igual a 0",
                    Alert.AlertType.ERROR);
            return;
        }

        if (productoEnEdicion == null) {
            listaCotejo.add(new ProductoDetalle(nombre, cantidad));
        } else {
            productoEnEdicion.setNombre(nombre);
            productoEnEdicion.setCantidad(cantidad);
            cancelarEdicion(null);
        }

        txtNombre.clear();
        txtCantidad.clear();
    }

    // =========================
    // SELECCIONAR PARA EDITAR
    // =========================
    @FXML
    void seleccionarProductoParaEditar(MouseEvent event) {
        ProductoDetalle seleccion = tvDetalleFactura.getSelectionModel().getSelectedItem();
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
    // PROCESAR COTEJO
    // =========================
    private ObservableList<ResultadoItem> procesarCotejo() {

        if (listaCotejo.isEmpty()) {
            mostrarMensaje("Error",
                    "No hay productos ingresados para cotejar.",
                    Alert.AlertType.WARNING);
            return null;
        }

        boolean coincide = true;
        ObservableList<ResultadoItem> resultados = FXCollections.observableArrayList();

        for (ProductoDetalle original : facturaOriginal) {

            ProductoDetalle recibido = listaCotejo.stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(original.getNombre()))
                    .findFirst()
                    .orElse(new ProductoDetalle(original.getNombre(), 0));

            ResultadoItem item = new ResultadoItem(
                    original.getNombre(),
                    original.getCantidad(),
                    recibido.getCantidad()
            );

            resultados.add(item);

            if (item.diferenciaProperty().get() != 0) {
                coincide = false;
            }
        }

        estadoFactura = coincide ? "COTEJADA" : "COTEJADA CON DIFERENCIAS";

        mostrarMensaje("Resultado",
                coincide
                        ? "Cotejo de factura exitoso"
                        : "Cotejo de factura con sobrantes o faltantes",
                coincide ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING);

        return resultados;
    }

    // =========================
    // FINALIZAR COTEJO
    // =========================
    @FXML
    void handleCotejarFactura(ActionEvent event) {

        ObservableList<ResultadoItem> resultados = procesarCotejo();
        if (resultados == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/proveedores/fxml/resultadoCotejo.fxml"));
            Parent root = loader.load();

            resultadoCotejoController controller = loader.getController();
            controller.setDatosResultado(
                    txtNumFactura.getText(),
                    estadoFactura,
                    resultados
            );

            NavigationUtil.openNewWindow(event, root, "Resultado Cotejo");

            // AL CERRAR LA VENTANA
            limpiarTodo();

        } catch (IOException e) {
            mostrarMensaje("Error Crítico",
                    "No se pudo abrir la ventana de reporte.",
                    Alert.AlertType.ERROR);
        }
    }

    // =========================
    // UTILIDADES
    // =========================
    private void limpiarListas() {
        listaCotejo.clear();
        listaOriginalDisplay.clear();
    }

    private void limpiarTodo() {

        limpiarListas();
        facturaOriginal.clear();

        txtNumFactura.clear();
        txtNombre.clear();
        txtCantidad.clear();

        estadoFactura = null;
        productoEnEdicion = null;

        bloquearIngresoProductos();
    }

    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
