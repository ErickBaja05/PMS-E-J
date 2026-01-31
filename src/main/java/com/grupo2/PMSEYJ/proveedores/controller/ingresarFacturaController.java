package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ingresarFacturaController implements Initializable {

    @FXML
    private Button btnAgregarItem;

    @FXML
    private Button btnGuardarFactura;

    @FXML
    private Button btnValidarFactura;

    @FXML
    private CheckBox checkIVA;

    @FXML
    private TableColumn<?, ?> colCajas;

    @FXML
    private TableColumn<?, ?> colCodigoBarras;

    @FXML
    private TableColumn<?, ?> colLote;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colPrecioCompra;

    @FXML
    private TableColumn<?, ?> colSubtotal;

    @FXML
    private TableColumn<?, ?> colVencimiento;

    @FXML
    private DatePicker dpVencimiento;

    @FXML
    private TableView<?> tvDetalleFactura;

    @FXML
    private TextField txtCantCajas;

    @FXML
    private TextField txtCodigoBarras;

    @FXML
    private TextField txtLote;

    @FXML
    private TextField txtNumFactura;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtRentabilidad;

    @FXML
    private TextField txtTamañoCaja;

    @FXML
    void handleAgregarProducto(ActionEvent event) {

    }

    @FXML
    void handleGuardarFactura(ActionEvent event) {

    }

    @FXML
    void handleValidarFactura(ActionEvent event) {

    }

    // --- NUEVO: Método auxiliar para bloquear/desbloquear ---
    private void bloquearControles(boolean bloquear) {
        // Si bloquear es TRUE (estado inicial):
        // Factura habilitada, resto deshabilitado.

        // Control de Factura (inverso al resto)
        txtNumFactura.setDisable(!bloquear);
        btnValidarFactura.setDisable(!bloquear);

        // Controles de producto y tabla
        txtCodigoBarras.setDisable(bloquear);
        txtLote.setDisable(bloquear);
        dpVencimiento.setDisable(bloquear);
        txtPrecioCompra.setDisable(bloquear);
        txtRentabilidad.setDisable(bloquear);
        txtCantCajas.setDisable(bloquear);
        tvDetalleFactura.setDisable(bloquear);
        txtTamañoCaja.setDisable(bloquear);

        // Botones de acción

        btnAgregarItem.setDisable(bloquear);
        btnGuardarFactura.setDisable(bloquear);

    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Sistema de Proveedores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private void limpiarCamposProducto() {
        txtCodigoBarras.clear();
        txtLote.clear();
        dpVencimiento.setValue(null);
        txtPrecioCompra.clear();
        txtRentabilidad.clear();
        txtCantCajas.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bloquearControles(true);
    }
}







