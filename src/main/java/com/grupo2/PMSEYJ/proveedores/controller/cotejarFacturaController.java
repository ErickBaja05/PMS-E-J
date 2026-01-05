package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    // Campos de texto y Botones
    @FXML private TextField txtNumFactura, txtNombre, txtCantidad;
    @FXML private Button btnAgregarItem, btnCancelarEdicion;

    // TABLA IZQUIERDA (ORIGINAL - NO EDITABLE)
    @FXML private TableView<ProductoDetalle> tvFacturaOriginal;
    @FXML private TableColumn<ProductoDetalle, String> colProductoOrig;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidadOrig;

    // TABLA DERECHA (FISICA - EDITABLE)
    @FXML private TableView<ProductoDetalle> tvDetalleFactura;
    @FXML private TableColumn<ProductoDetalle, String> colProducto;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidad;

    // Listas observables para las tablas
    private ObservableList<ProductoDetalle> listaCotejo = FXCollections.observableArrayList();
    private ObservableList<ProductoDetalle> listaOriginalDisplay = FXCollections.observableArrayList();

    // Lógica interna
    private List<ProductoDetalle> facturaOriginal = new ArrayList<>(); // Respaldo de datos
    private String estadoFactura = "";
    private ProductoDetalle productoEnEdicion = null; // Controla si estamos editando

    @FXML
    public void initialize() {
        // Configurar columnas Tabla Izquierda (Original)
        colProductoOrig.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidadOrig.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tvFacturaOriginal.setItems(listaOriginalDisplay);

        // Configurar columnas Tabla Derecha (Física)
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tvDetalleFactura.setItems(listaCotejo);

        // Placeholder para tablas vacías
        tvFacturaOriginal.setPlaceholder(new Label("Busque una factura para ver el detalle"));
        tvDetalleFactura.setPlaceholder(new Label("No hay productos contados aún"));
    }

    @FXML
    void buscarFactura(ActionEvent event) {
        String numFactura = txtNumFactura.getText().trim();
public class cotejarFacturaController {

    @FXML private TextField txtNumFactura, txtNombre, txtCantidad;
    @FXML private TableView<ProductoDetalle> tvDetalleFactura;
    @FXML private TableColumn<ProductoDetalle, String> colProducto;
    @FXML private TableColumn<ProductoDetalle, Integer> colCantidad;

    // Lista de la tabla (lo que el usuario ingresa)
    private ObservableList<ProductoDetalle> listaCotejo = FXCollections.observableArrayList();

    // Lista simulada de la Base de Datos (lo que la factura dice que debe venir)
    private List<ProductoDetalle> facturaOriginal = new ArrayList<>();
    private String estadoFactura = "";

    @FXML
    public void initialize() {
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tvDetalleFactura.setItems(listaCotejo);
    }

    @FXML
    void buscarFactura(ActionEvent event) {
        String numFactura = txtNumFactura.getText().trim();
        if (numFactura.isEmpty()) {
            mostrarMensaje("Error", "Ingrese un número de factura.", Alert.AlertType.WARNING);
            return;
        }

        // SIMULACIÓN DE BASE DE DATOS
        if (numFactura.equals("F-001")) {
            estadoFactura = "NO COTEJADA";

            estadoFactura = "NO COTEJADA"; // Simulación de atributo

            // Validar Estado
            if (!estadoFactura.equals("NO COTEJADA")) {
                mostrarMensaje("Aviso", "La factura ya fue cotejada previamente.", Alert.AlertType.WARNING);
                return;
            }

            // 1. Limpiar todo antes de cargar nueva
            limpiarListas();
            cancelarEdicion(null);

            // 2. Cargar datos originales (Simulados)
            facturaOriginal.clear();
            facturaOriginal.add(new ProductoDetalle("Paracetamol", 10));
            facturaOriginal.add(new ProductoDetalle("Ibuprofeno", 5));
            facturaOriginal.add(new ProductoDetalle("Amoxicilina", 20));

            // 3. Mostrar en la tabla izquierda
            listaOriginalDisplay.setAll(facturaOriginal);

            mostrarMensaje("Éxito", "Factura cargada. Proceda a ingresar la mercancía física.", Alert.AlertType.INFORMATION);
            //mostrarMensaje("Info", "Factura encontrada", Alert.AlertType.INFORMATION); // Mensaje solicitado
        } else {
            mostrarMensaje("Error", "Factura no encontrada.", Alert.AlertType.ERROR);
        }
    }

    // Método activado al hacer clic en la tabla derecha
    @FXML
    void seleccionarProductoParaEditar(MouseEvent event) {
        ProductoDetalle seleccion = tvDetalleFactura.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            productoEnEdicion = seleccion;
            txtNombre.setText(seleccion.getNombre());
            txtCantidad.setText(String.valueOf(seleccion.getCantidad()));

            // Cambiar estado visual a MODO EDICIÓN
            btnAgregarItem.setText("ACTUALIZAR");
            btnAgregarItem.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;"); // Naranja para editar
            btnCancelarEdicion.setDisable(false);
        }
    }

    @FXML
    void cancelarEdicion(ActionEvent event) {
        productoEnEdicion = null;
        txtNombre.clear();
        txtCantidad.clear();
        tvDetalleFactura.getSelectionModel().clearSelection();

        // Restaurar botón a MODO AGREGAR
        btnAgregarItem.setText("AGREGAR");
        btnAgregarItem.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;"); // Azul original
        btnCancelarEdicion.setDisable(true);
            // Cargar datos originales que se supone deben venir en la factura
            facturaOriginal.clear();
            facturaOriginal.add(new ProductoDetalle("Paracetamol", 10));
            facturaOriginal.add(new ProductoDetalle("Ibuprofeno", 5));

            mostrarMensaje("Éxito", "Factura cargada. Proceda a ingresar la mercancía física.", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "Factura no encontrada.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        if (estadoFactura.isEmpty()) {
            mostrarMensaje("Error", "Primero debe buscar una factura válida.", Alert.AlertType.ERROR);
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            if (nombre.isEmpty()) {
                mostrarMensaje("Error", "El nombre del producto no puede estar vacío.", Alert.AlertType.WARNING);
                return;
            }

            if (productoEnEdicion == null) {
                // --- MODO AGREGAR NUEVO ---
                listaCotejo.add(new ProductoDetalle(nombre, cantidad));
                mostrarMensaje("Éxito", "Producto agregado", Alert.AlertType.INFORMATION); // Mensaje solicitado
            } else {
                // --- MODO ACTUALIZAR ---
                productoEnEdicion.setNombre(nombre);
                productoEnEdicion.setCantidad(cantidad);
                tvDetalleFactura.refresh(); // Refrescar vista de tabla
                mostrarMensaje("Éxito", "Producto actualizado correctamente", Alert.AlertType.INFORMATION);

                cancelarEdicion(null); // Volver al estado inicial
            }

            // Limpiar campos si fue agregado nuevo (si fue edición, cancelarEdicion ya limpia)
            if (productoEnEdicion == null) {
                txtNombre.clear();
                txtCantidad.clear();
                txtNombre.requestFocus();
            }

            listaCotejo.add(new ProductoDetalle(nombre, cantidad));

            txtNombre.clear();
            txtCantidad.clear();
            txtNombre.requestFocus();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Cantidad inválida.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleCotejarFactura(ActionEvent event) throws IOException {
        if (listaCotejo.isEmpty()) {
            mostrarMensaje("Error", "No hay productos ingresados para comparar.", Alert.AlertType.WARNING);
            return;
        }

        boolean coincidenTodas = compararListas();

        if (coincidenTodas) {
            // ESCENARIO BÁSICO
            estadoFactura = "COTEJADA";
            mostrarMensaje("Resultado", "La mercancía recibida coincide con la factura.", Alert.AlertType.INFORMATION);
            mostrarMensaje("Éxito", "Cotejo de factura exitoso.", Alert.AlertType.INFORMATION);
        } else {
            // ESCENARIO ALTERNATIVO (Inconsistencias)
            estadoFactura = "COTEJADO CON DETALLE";
            mostrarMensaje("Discrepancia", "La cantidad comprada y la cantidad recibida no coinciden.", Alert.AlertType.WARNING);
            mostrarMensaje("Éxito", "Cotejo de factura con sobrantes o excedentes.", Alert.AlertType.INFORMATION);
        }

        // Cargar siguiente pantalla
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proveedores/fxml/resultadoCotejo.fxml"));
            Parent root = loader.load();
            NavigationUtil.openNewWindow(event, root, "Resultado Cotejo");
            limpiarListas();
            limpiarTodo();
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo cargar la ventana de resultados: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean compararListas() {
        // Nota: Esta lógica es estricta (debe coincidir tamaño exacto y contenido)
        if (listaCotejo.size() != facturaOriginal.size()) return false;

        for (ProductoDetalle original : facturaOriginal) {
            boolean encontrado = listaCotejo.stream().anyMatch(p ->
                    p.getNombre().equalsIgnoreCase(original.getNombre()) &&
                            p.getCantidad() == original.getCantidad()
            );
            if (!encontrado) return false;
        }
        return true;
    }

    private void mostrarMensaje(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

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

    // Clase Modelo interna
    public static class ProductoDetalle {
        private String nombre;
        private int cantidad;

        public ProductoDetalle(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; } // Necesario set para editar

        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; } // Necesario set para editar
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/proveedores/fxml/resultadoCotejo.fxml"));
        Parent root = loader.load();
        NavigationUtil.openNewWindow(event,root,"Resultado Cotejo");
        limpiarTodo();
    }

    private boolean compararListas() {
        if (listaCotejo.size() != facturaOriginal.size()) return false;

        for (int i = 0; i < facturaOriginal.size(); i++) {
            ProductoDetalle original = facturaOriginal.get(i);
            // Buscamos si el producto ingresado coincide en nombre y cantidad
            boolean encontrado = listaCotejo.stream().anyMatch(p ->
                    p.getNombre().equalsIgnoreCase(original.getNombre()) &&
                            p.getCantidad() == original.getCantidad()
            );

            if (!encontrado) return false;
        }
        return true;
    }

    private void mostrarMensaje(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    private void limpiarTodo() {
        txtNumFactura.clear();
        listaCotejo.clear();
        facturaOriginal.clear();
        estadoFactura = "";
    }

    public static class ProductoDetalle {
        private String nombre;
        private int cantidad;
        public ProductoDetalle(String nombre, int cantidad) { this.nombre = nombre; this.cantidad = cantidad; }
        public String getNombre() { return nombre; }
        public int getCantidad() { return cantidad; }
    }
}
