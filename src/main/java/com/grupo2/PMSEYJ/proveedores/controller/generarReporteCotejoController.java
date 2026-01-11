package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.model.DetalleReporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class generarReporteCotejoController {

    @FXML private TextField txtInputFactura;
    @FXML private Label lblFacturaActual, lblEstadoActual;

    @FXML private TableView<DetalleReporte> tvReporte;
    @FXML private TableColumn<DetalleReporte, String> colCodigo, colEstadoProducto;
    @FXML private TableColumn<DetalleReporte, Integer> colUnidadesCompradas, colCantidadReal, colDiferencia;

    private final ObservableList<DetalleReporte> listaReporte =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // ===== CONFIGURACIÓN CON LAMBDAS + PROPERTIES =====
        colCodigo.setCellValueFactory(cell -> cell.getValue().codigoProperty());

        colUnidadesCompradas.setCellValueFactory(cell -> cell.getValue().compradasProperty().asObject());

        colCantidadReal.setCellValueFactory(cell -> cell.getValue().realProperty().asObject());

        colDiferencia.setCellValueFactory(cell -> cell.getValue().diferenciaProperty().asObject());

        colEstadoProducto.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        tvReporte.setItems(listaReporte);
        tvReporte.setPlaceholder(new Label("No hay datos para mostrar"));
    }

    @FXML
    void handleGenerarReporte(ActionEvent event) {

        String numFactura = txtInputFactura.getText().trim();
        listaReporte.clear();
        lblFacturaActual.setText("---");
        lblEstadoActual.setText("---");

        if (numFactura.isEmpty()) {
            mostrarAlerta("Por favor ingrese un número de factura.", Alert.AlertType.WARNING);
            return;
        }

        if (!validarExistenciaFactura(numFactura)) {
            mostrarAlerta("Factura no registrada", Alert.AlertType.ERROR);
            return;
        }

        String estado = obtenerEstadoFactura(numFactura);
        if ("NO COTEJADA".equals(estado)) {
            mostrarAlerta("Esta factura aún no ha sido cotejada", Alert.AlertType.WARNING);
            return;
        }

        lblFacturaActual.setText(numFactura);
        lblEstadoActual.setText(estado);

        cargarDatosSimulados();

        System.out.println(
                "LOG: Generación de reporte | Factura: " + numFactura +
                        " | Usuario: Admin | Fecha: " + LocalDateTime.now()
        );

        mostrarAlerta("Reporte generado exitosamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void handleImprimir(ActionEvent event) {
        if (listaReporte.isEmpty()) {
            mostrarAlerta("No hay datos para imprimir.", Alert.AlertType.WARNING);
            return;
        }
        mostrarAlerta("Enviando reporte a la impresora...", Alert.AlertType.INFORMATION);
    }

    @FXML
    void handleCerrar(ActionEvent event) {
        ((Stage) txtInputFactura.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ===== MÉTODOS SIMULADOS =====
    private boolean validarExistenciaFactura(String factura) {
        return factura.equals("F-001") || factura.equals("F-002");
    }

    private String obtenerEstadoFactura(String factura) {
        if (factura.equals("F-002")) return "NO COTEJADA";
        return "COTEJADA CON DIFERENCIAS";
    }

    private void cargarDatosSimulados() {
        listaReporte.add(new DetalleReporte("AUX-101", 100, 100, 0, "CORRECTO"));
        listaReporte.add(new DetalleReporte("AUX-205", 50, 48, -2, "FALTANTE"));
        listaReporte.add(new DetalleReporte("AUX-309", 20, 25, 5, "EXCEDENTE"));
    }
}
