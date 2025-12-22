package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

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
            estadoFactura = "NO COTEJADA"; // Simulación de atributo

            // Validar Estado
            if (!estadoFactura.equals("NO COTEJADA")) {
                mostrarMensaje("Aviso", "La factura ya fue cotejada previamente.", Alert.AlertType.WARNING);
                return;
            }

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

            listaCotejo.add(new ProductoDetalle(nombre, cantidad));

            txtNombre.clear();
            txtCantidad.clear();
            txtNombre.requestFocus();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Cantidad inválida.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleCotejarFactura(ActionEvent event) {
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

        System.out.println("Nuevo estado de la factura: " + estadoFactura);
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