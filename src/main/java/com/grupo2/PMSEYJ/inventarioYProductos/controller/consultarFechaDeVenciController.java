package com.grupo2.PMSEYJ.inventarioYProductos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class consultarFechaDeVenciController {

    @FXML private RadioButton rbPorCodigo, rbPorNombre;
    @FXML private VBox vboxCodigo, vboxNombre;
    @FXML private TextField txtCodigoBarras, txtLoteCodigo, txtNombreProducto, txtLoteNombre;
    @FXML private Label lblFechaVencimiento;

    /**
     * Alterna el bloqueo de las columnas según el RadioButton seleccionado
     */
    @FXML
    void handleCambioSeleccion(ActionEvent event) {
        if (rbPorCodigo.isSelected()) {
            vboxCodigo.setDisable(false);
            vboxCodigo.setOpacity(1.0);
            vboxNombre.setDisable(true);
            vboxNombre.setOpacity(0.4);
            txtNombreProducto.clear();
            txtLoteNombre.clear();
        } else {
            vboxCodigo.setDisable(true);
            vboxCodigo.setOpacity(0.4);
            txtCodigoBarras.clear();
            txtLoteCodigo.clear();
            vboxNombre.setDisable(false);
            vboxNombre.setOpacity(1.0);
        }
    }

    @FXML
    void handleConsultar(ActionEvent event) {
        if (rbPorCodigo.isSelected()) {
            consultarPorCodigo();
        } else {
            consultarPorNombre();
        }
    }

    private void consultarPorCodigo() {
        String codigo = txtCodigoBarras.getText().trim();
        String lote = txtLoteCodigo.getText().trim();

        if (codigo.isEmpty()) {
            mostrarAlerta("Error", "Código no válido, producto no encontrado", Alert.AlertType.ERROR); //
        } else if (lote.isEmpty()) {
            mostrarAlerta("Error", "Número de lote no válido, lote no encontrado", Alert.AlertType.ERROR); //
        } else {
            // Éxito: Simulación de recuperación de lote
            lblFechaVencimiento.setText("2026-05-15"); //
        }
    }

    private void consultarPorNombre() {
        String nombre = txtNombreProducto.getText().trim();
        String lote = txtLoteNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "Nombre no válido, producto no encontrado", Alert.AlertType.ERROR); //
        } else if (lote.isEmpty()) {
            mostrarAlerta("Error", "Número de lote no válido, lote no encontrado", Alert.AlertType.ERROR); //
        } else {
            // Éxito: Simulación de recuperación de lote
            lblFechaVencimiento.setText("2025-12-01"); //
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
