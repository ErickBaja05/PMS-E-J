package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;

import com.grupo2.PMSEYJ.ventasYFacturacion.caja.model.MovimientoCaja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class realizarCierreDeCajaController {

    // ===============================
    // COMPONENTES FXML
    // ===============================
    @FXML private TableView<MovimientoCaja> tablaMovimientos;
    @FXML private TableColumn<MovimientoCaja, String> colTipo;
    @FXML private TableColumn<MovimientoCaja, Double> colMonto;
    @FXML private TextField txtSaldoFinal;

    // ===============================
    // DATOS
    // ===============================
    private final ObservableList<MovimientoCaja> listaMovimientos =
            FXCollections.observableArrayList();

    // Simulación del estado de la caja
    private boolean cajaYaCerrada = false;

    // ===============================
    // ALERTAS
    // ===============================
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ===============================
    // INICIALIZADOR
    // ===============================
    @FXML
    public void initialize() {

        // CONFIGURACIÓN DE TABLA (LAMBDA + PROPERTIES)
        colTipo.setCellValueFactory(cell -> cell.getValue().tipoProperty());
        colMonto.setCellValueFactory(cell -> cell.getValue().montoProperty().asObject());

        tablaMovimientos.setItems(listaMovimientos);

        // VALORES QUEMADOS (REPORTE SIMULADO)
        if (!cajaYaCerrada) {
            cargarReporteMovimientos();
        }
    }

    // ===============================
    // CIERRE DE CAJA
    // ===============================
    @FXML
    void handleCerrarCaja(ActionEvent event) {

        // 1. Verificación del estado de la Caja
        if (cajaYaCerrada) {
            mostrarAlerta("Información", "La caja ya se encuentra cerrada", Alert.AlertType.INFORMATION);
            return;
        }

        String inputSaldo = txtSaldoFinal.getText().trim();

        // 2. Validación campo vacío
        if (inputSaldo.isEmpty()) {
            mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
            return;
        }

        try {
            double saldoFinal = Double.parseDouble(inputSaldo);

            // 3. Validación saldo > 0
            if (saldoFinal > 0) {

                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmar Cierre");
                confirmacion.setContentText(
                        "¿Está seguro que desea cerrar la caja con un saldo de $" + saldoFinal + "?"
                );

                Optional<ButtonType> resultado = confirmacion.showAndWait();

                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    // ESCENARIO BÁSICO
                    cajaYaCerrada = true;
                    txtSaldoFinal.setEditable(false);

                    // LIMPIAR TODO
                    limpiarFormulario();

                    mostrarAlerta("Éxito", "Caja cerrada exitosamente", Alert.AlertType.INFORMATION);
                }

            } else {
                mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
        }
    }

    // ===============================
    // CARGA DE MOVIMIENTOS (SIMULADO)
    // ===============================
    private void cargarReporteMovimientos() {

        listaMovimientos.clear();

        listaMovimientos.addAll(
                new MovimientoCaja("Venta", 120.50),
                new MovimientoCaja("Avance", 50.00),
                new MovimientoCaja("Vale", 30.75),
                new MovimientoCaja("Venta", 200.00)
        );
    }

    // ===============================
    // LIMPIEZA TOTAL
    // ===============================
    private void limpiarFormulario() {
        txtSaldoFinal.clear();
        tablaMovimientos.getItems().clear();
    }
}
