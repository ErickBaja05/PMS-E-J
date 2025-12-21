package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class cotejarFacturaController {

    @FXML private TextField txtNumeroFactura;
    @FXML private TableView<Object> tablaCotejo; // Reemplazar Object por tu modelo (ej. DetalleFactura)
    @FXML private TableColumn<Object, String> colProducto;
    @FXML private TableColumn<Object, Integer> colUnidadesCompradas;
    @FXML private TableColumn<Object, String> colCantidadReal; // Usualmente un TextField dentro de la celda
    @FXML private Label lblEstadoFactura;

    // --- MÉTODOS DE APOYO PARA MENSAJES ---

    private void mostrarMensaje(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    // --- ACCIONES ---

    @FXML
    void handleBuscarFactura(ActionEvent event) {
        String numFactura = txtNumeroFactura.getText().trim();

        if (numFactura.isEmpty()) {
            mostrarMensaje("Error", "Debe ingresar un número de factura.", Alert.AlertType.WARNING);
            return;
        }

        // Simulación de búsqueda (Escenario Alternativo 1)
        boolean existeFactura = numFactura.equals("F-001");

        if (!existeFactura) {
            mostrarMensaje("No Encontrado", "No existe una factura de compra a proveedor con el número de factura ingresado", Alert.AlertType.ERROR);
            return;
        }

        // Verificación de Estado (Escenario Alternativo 2)
        String estadoFactura = "NO COTEJADA"; // Simulación de atributo de la base

        if (!estadoFactura.equals("NO COTEJADA")) {
            mostrarMensaje("Aviso", "La factura ya fue cotejada", Alert.AlertType.WARNING);
            return;
        }

        // ESCENARIO BÁSICO Paso 4: Presentar detalle
        lblEstadoFactura.setText("Estado: Factura cargada. Ingrese cantidades recibidas.");
        cargarDetalleTabla();
    }

    @FXML
    void handleFinalizarCotejo(ActionEvent event) {
        if (tablaCotejo.getItems().isEmpty()) {
            mostrarMensaje("Error", "No hay datos para cotejar.", Alert.AlertType.WARNING);
            return;
        }

        // 1. Simulación de validación de cantidad real (Escenario Alternativo 3)
        // Aquí recorrerías la tabla validando que cada celda tenga un número entero >= 0
        boolean cantidadesValidas = true;
        if (!cantidadesValidas) {
            mostrarMensaje("Error", "La cantidad debe ser un numero entero mayor o igual a 0", Alert.AlertType.ERROR);
            return;
        }

        // 2. Simulación de comparación (Pasos 7-9 y Escenario Alternativo 4)
        boolean coincidenTodas = true; // Supongamos que comparamos unidadesCompradas vs cantidadReal

        if (coincidenTodas) {
            // ESCENARIO BÁSICO
            mostrarMensaje("Resultado", "La mercancía recibida coincide con la factura", Alert.AlertType.INFORMATION);
            mostrarMensaje("Éxito", "Cotejo de factura exitoso", Alert.AlertType.INFORMATION);
            // El Sistema cambia estado a "COTEJADA"
        } else {
            // ESCENARIO ALTERNATIVO 4
            mostrarMensaje("Discrepancia", "La cantidad comprada y la cantidad recibida no coinciden", Alert.AlertType.WARNING);
            mostrarMensaje("Éxito", "Cotejo de factura con sobrantes o excedentes", Alert.AlertType.INFORMATION);
            // El Sistema cambia estado a "COTEJADO CON DETALLE"
        }

        // Registro de auditoría (login y timestamp) y limpieza
        limpiarCampos();
    }

    private void cargarDetalleTabla() {
        // Lógica para llenar la tabla con productos simulados
        System.out.println("Cargando productos de la factura...");
    }

    private void limpiarCampos() {
        txtNumeroFactura.clear();
        tablaCotejo.getItems().clear();
        lblEstadoFactura.setText("Estado: Esperando búsqueda...");
    }
}