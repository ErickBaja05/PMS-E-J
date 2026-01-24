package com.grupo2.PMSEYJ.inventarioYProductos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class modificarProductoController implements Initializable {

    // Se eliminó txtStock de la lista de FXML
    @FXML private TextField txtBusquedaCodigo, txtNombre, txtPrecioVenta, txtPVP;
    @FXML private TextArea txtDescripcion;
    @FXML private ComboBox<String> cmbFormaVenta, cmbTipoVenta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbFormaVenta.getItems().addAll("POR UNIDAD", "POR CAJA");
        cmbTipoVenta.getItems().addAll("OTC", "PRESENTACION DE RECETA", "RETENCION DE RECETA");
    }

    private void mostrarAlerta(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    @FXML
    void buscarProducto(ActionEvent event) {
        String codigo = txtBusquedaCodigo.getText().trim();
        if (codigo.isEmpty()) {
            mostrarAlerta("Campo Requerido", "Por favor, ingrese un código auxiliar para realizar la búsqueda.", Alert.AlertType.WARNING);
            txtBusquedaCodigo.requestFocus();
            return;
        }

        // Simulación de búsqueda
        boolean existe = codigo.equals("PROD001");

        if (existe) {
            txtNombre.setText("Paracetamol 500mg");
            // Se eliminó la línea de txtStock.setText
            txtPrecioVenta.setText("0.50");
            txtPVP.setText("0.75");
            txtDescripcion.setText("Analgésico y antipirético de uso común");
            cmbFormaVenta.setValue("POR UNIDAD");
            cmbTipoVenta.setValue("OTC");
        } else {
            mostrarAlerta("Error", "No existe un producto con el código ingresado", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void guardarModificaciones(ActionEvent event) {
        // 1. Validar Nombre
        String nombre = txtNombre.getText().trim();
        if (nombre.length() < 5 || nombre.length() > 50) {
            mostrarAlerta("Error de Nombre", "El nombre del producto debe ser una cadena de mínimo 5 y máximo 50 caracteres", Alert.AlertType.ERROR);
            return;
        }

        // --- VALIDACIÓN DE STOCK ELIMINADA ---

        // 2. Validar Precios (Decimal > 0)
        try {
            double precio = Double.parseDouble(txtPrecioVenta.getText().trim());
            double pvp = Double.parseDouble(txtPVP.getText().trim());
            if (precio <= 0 || pvp <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Precio", "El precio de venta y el PVP deben ser números decimales mayores que 0", Alert.AlertType.ERROR);
            return;
        }

        // 3. Validar Descripción
        String desc = txtDescripcion.getText().trim();
        if (!desc.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{10,100}$")) {
            mostrarAlerta("Error de Descripción", "La descripción debe tener al menos 10 y máximo 100 caracteres (solo letras y espacios)", Alert.AlertType.ERROR);
            return;
        }

        // 4. Validar ComboBoxes
        if (cmbFormaVenta.getValue() == null || cmbTipoVenta.getValue() == null) {
            mostrarAlerta("Error de Selección", "Debe seleccionar una forma y tipo de venta válidos", Alert.AlertType.ERROR);
            return;
        }

        mostrarAlerta("Éxito", "Producto modificado exitosamente", Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    @FXML
    void limpiarCampos() {
        mostrarAlerta("Operacion Cancelada", "No se realizaron cambios", Alert.AlertType.INFORMATION);
        txtBusquedaCodigo.clear();
        txtNombre.clear();
        txtPrecioVenta.clear();
        txtPVP.clear();
        txtDescripcion.clear();
        cmbFormaVenta.getSelectionModel().clearSelection();
        cmbTipoVenta.getSelectionModel().clearSelection();
    }
}