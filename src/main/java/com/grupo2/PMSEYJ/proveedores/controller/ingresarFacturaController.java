package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.model.DetalleFactura;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ingresarFacturaController {

    @FXML private TextField txtNumFactura, txtCodAuxiliar, txtLote,
            txtPrecioCompra, txtRentabilidad, txtCantCajas;
    @FXML private DatePicker dpVencimiento;

    // --- NUEVO: Necesitamos referencia a los botones para bloquearlos/desbloquearlos ---
    // Asegúrate de poner estos fx:id en tu SceneBuilder
    @FXML private Button btnValidar; // Botón que valida la factura
    @FXML private Button btnAgregar; // Botón que agrega productos
    @FXML private Button btnGuardar; // Botón que guarda la factura

    @FXML private TableView<DetalleFactura> tvDetalleFactura;
    @FXML private TableColumn<DetalleFactura, String> colProducto, colLote, colVencimiento;
    @FXML private TableColumn<DetalleFactura, Double> colPrecioCompra, colSubtotal;
    @FXML private TableColumn<DetalleFactura, Integer> colCajas;

    private final ObservableList<DetalleFactura> masterData = FXCollections.observableArrayList();

    // --- VALORES QUEMADOS PARA PRUEBAS ---
    private final List<String> facturasRegistradas = Arrays.asList("101", "102");
    private final List<String> productosExistentes = Arrays.asList("A1", "B2", "C3");
    private final List<String> lotesRegistrados = Arrays.asList("A1-L01", "B2-L99");

    @FXML
    public void initialize() {

        // 🔹 Enlace correcto (JavaFX Properties)
        colProducto.setCellValueFactory(c -> c.getValue().nombreProperty());
        colLote.setCellValueFactory(c -> c.getValue().loteProperty());
        colVencimiento.setCellValueFactory(c -> c.getValue().vencimientoProperty());
        colPrecioCompra.setCellValueFactory(c -> c.getValue().costoProperty().asObject());
        colCajas.setCellValueFactory(c -> c.getValue().cajasProperty().asObject());
        colSubtotal.setCellValueFactory(c -> c.getValue().precioVentaProperty().asObject());

        tvDetalleFactura.setItems(masterData);

        // --- NUEVO: Al iniciar, bloqueamos todo excepto la factura ---
        bloquearControles(true);
    }

    // --- NUEVO: Método auxiliar para bloquear/desbloquear ---
    private void bloquearControles(boolean bloquear) {
        // Si bloquear es TRUE (estado inicial):
        // Factura habilitada, resto deshabilitado.

        // Control de Factura (inverso al resto)
        txtNumFactura.setDisable(!bloquear);
        if(btnValidar != null) btnValidar.setDisable(!bloquear);

        // Controles de producto y tabla
        txtCodAuxiliar.setDisable(bloquear);
        txtLote.setDisable(bloquear);
        dpVencimiento.setDisable(bloquear);
        txtPrecioCompra.setDisable(bloquear);
        txtRentabilidad.setDisable(bloquear);
        txtCantCajas.setDisable(bloquear);
        tvDetalleFactura.setDisable(bloquear);

        // Botones de acción
        if(btnAgregar != null) btnAgregar.setDisable(bloquear);
        if(btnGuardar != null) btnGuardar.setDisable(bloquear);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Sistema de Proveedores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void handleValidarFactura(ActionEvent event) {

        String numFact = txtNumFactura.getText().trim();

        // Escenario Alternativo 1
        try {
            int fact = Integer.parseInt(numFact);
            if (fact <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Número de factura no válido", Alert.AlertType.ERROR);
            return;
        }

        // Escenario Alternativo 2
        if (facturasRegistradas.contains(numFact)) {
            mostrarAlerta("Factura ya ingresada", Alert.AlertType.WARNING);
            return;
        }

        // --- NUEVO: Si pasa las validaciones originales, desbloqueamos ---
        bloquearControles(false);

        mostrarAlerta("Factura válida para ingreso", Alert.AlertType.INFORMATION);
    }

    @FXML
    void handleAgregarProducto(ActionEvent event) {

        // Alternativo 3
        String codAux = txtCodAuxiliar.getText().trim();
        if (!productosExistentes.contains(codAux)) {
            mostrarAlerta("Producto no registrado", Alert.AlertType.ERROR);
            return;
        }

        // Alternativo 4 y 5
        String numLote = txtLote.getText().trim();
        try {
            if (Integer.parseInt(numLote) <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Número de lote no válido", Alert.AlertType.ERROR);
            return;
        }

        if (lotesRegistrados.contains(codAux + "-" + numLote)) {
            mostrarAlerta("Lote ya ingresado", Alert.AlertType.ERROR);
            return;
        }

        // Alternativo 6 y 7
        LocalDate fechaVenc = dpVencimiento.getValue();
        if (fechaVenc == null) {
            mostrarAlerta("Fecha no válida, ingrese una fecha en formato (dd-mm-aaaa)", Alert.AlertType.ERROR);
            return;
        }

        if (!fechaVenc.isAfter(LocalDate.now())) {
            mostrarAlerta("No se pueden ingresar productos caducados", Alert.AlertType.ERROR);
            return;
        }

        // Alternativo 8
        double pCompra;
        try {
            pCompra = Double.parseDouble(txtPrecioCompra.getText());
            if (pCompra <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("El precio ingresado no es válido", Alert.AlertType.ERROR);
            return;
        }

        // Alternativo 9
        int rent;
        try {
            rent = Integer.parseInt(txtRentabilidad.getText());
            if (rent < 0 || rent > 100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Porcentaje no válido, ingrese un número entre 0 y 100", Alert.AlertType.ERROR);
            return;
        }

        // Alternativo 10
        int cajas;
        try {
            cajas = Integer.parseInt(txtCantCajas.getText());
            if (cajas <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Cantidad no válida, ingrese un número entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }

        // Escenario Básico
        double pVenta = pCompra + (pCompra * rent / 100.0);
        String fechaStr = fechaVenc.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        masterData.add(
                new DetalleFactura(
                        "Producto " + codAux,
                        numLote,
                        fechaStr,
                        pCompra,
                        cajas,
                        pVenta
                )
        );

        limpiarCamposProducto();
    }

    @FXML
    void handleGuardarFactura(ActionEvent event) {

        if (masterData.isEmpty()) {
            mostrarAlerta("Debe agregar al menos un producto a la tabla", Alert.AlertType.WARNING);
            return;
        }

        mostrarAlerta("Factura ingresada con éxito, stock actualizado", Alert.AlertType.INFORMATION);

        System.out.println("Acción registrada: Ingreso de factura " + txtNumFactura.getText());

        masterData.clear();
        txtNumFactura.clear();
        limpiarCamposProducto(); // Aseguramos limpiar campos

        // --- NUEVO: Volvemos a bloquear todo esperando nueva factura ---
        bloquearControles(true);
    }

    private void limpiarCamposProducto() {
        txtCodAuxiliar.clear();
        txtLote.clear();
        dpVencimiento.setValue(null);
        txtPrecioCompra.clear();
        txtRentabilidad.clear();
        txtCantCajas.clear();
    }
}