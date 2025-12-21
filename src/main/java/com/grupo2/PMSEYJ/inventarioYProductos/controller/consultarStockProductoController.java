package com.grupo2.PMSEYJ.inventarioYProductos.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class consultarStockProductoController {

    @FXML private RadioButton rbPorLote, rbStockTotal;
    @FXML private VBox vboxLote, vboxTotal;
    @FXML private TextField txtCodigoBarras, txtNumeroLote, txtNombreProducto;
    @FXML private Label lblResultadoStock;

    @FXML
    void handleCambioSeleccion(ActionEvent event) {
        if (rbPorLote.isSelected()) {
            // Habilitar Lote, Bloquear Total
            vboxLote.setDisable(false);
            vboxLote.setOpacity(1.0);

            vboxTotal.setDisable(true);
            vboxTotal.setOpacity(0.4);
            txtNombreProducto.clear();
        } else {
            // Habilitar Total, Bloquear Lote
            vboxLote.setDisable(true);
            vboxLote.setOpacity(0.4);
            txtCodigoBarras.clear();
            txtNumeroLote.clear();

            vboxTotal.setDisable(false);
            vboxTotal.setOpacity(1.0);
        }
    }

    @FXML
    void handleConsultar(ActionEvent event) {
        // Lógica según Caso de Uso 1: Lote
        if (rbPorLote.isSelected()) {
            String codigo = txtCodigoBarras.getText().trim();
            String lote = txtNumeroLote.getText().trim();

            if (codigo.isEmpty()) {
                mostrarAlerta("Error", "Código no válido, producto no encontrado", Alert.AlertType.ERROR);
            } else if (lote.isEmpty()) {
                mostrarAlerta("Error", "Número de lote no válido, lote no encontrado", Alert.AlertType.ERROR);
            } else {
                lblResultadoStock.setText("50 unidades");
            }
        }
        // Lógica según Caso de Uso 2: Stock Total
        else if (rbStockTotal.isSelected()) {
            String nombre = txtNombreProducto.getText().trim();

            if (nombre.isEmpty()) {
                mostrarAlerta("Error", "Nombre no válido, producto no encontrado", Alert.AlertType.ERROR);
            } else {
                lblResultadoStock.setText("125 unidades");
            }
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