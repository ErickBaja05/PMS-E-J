package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class realizarCierreDeCajaController {

    @FXML private TableView<Object> tablaMovimientos;
    @FXML private TableColumn<Object, String> colTipo;
    @FXML private TableColumn<Object, Double> colMonto;
    @FXML private TextField txtSaldoFinal;

    // Simulación del estado de la caja para la interfaz
    private boolean cajaYaCerrada = false;

    /**
     * Método para mostrar alertas de acuerdo a los escenarios
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void handleCerrarCaja(ActionEvent event) {
        // 1. Verificación del estado de la Caja (Paso 1 del caso de uso)
        if (cajaYaCerrada) {
            // ESCENARIO ALTERNATIVO 1: Caja ya cerrada
            mostrarAlerta("Información", "La caja ya se encuentra cerrada", Alert.AlertType.INFORMATION);
            return;
        }

        String inputSaldo = txtSaldoFinal.getText().trim();

        // 2. Validación de campo vacío o formato decimal (Paso 4)
        if (inputSaldo.isEmpty()) {
            mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
            return;
        }

        try {
            double saldoFinal = Double.parseDouble(inputSaldo);

            // 3. Validación de saldo mayor que 0 (Paso 5)
            if (saldoFinal > 0) {
                // Confirmación previa al registro (Buena práctica de interfaz)
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmar Cierre");
                confirmacion.setContentText("¿Está seguro que desea cerrar la caja con un saldo de $" + saldoFinal + "?");

                Optional<ButtonType> resultado = confirmacion.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    // ESCENARIO BÁSICO: Cierre exitoso
                    cajaYaCerrada = true;
                    txtSaldoFinal.setEditable(false);
                    mostrarAlerta("Éxito", "Caja cerrada exitosamente", Alert.AlertType.INFORMATION);
                }
            } else {
                // ESCENARIO ALTERNATIVO 3: Saldo decimal pero no mayor a 0
                mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            // ESCENARIO ALTERNATIVO 2: No es un número decimal
            mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
        }
    }

    /**
     * Método inicializador para cargar el reporte si la caja está abierta
     */
    @FXML
    public void initialize() {
        // Según Paso 2 del Escenario Básico, si no está cerrada, mostrar reporte
        if (!cajaYaCerrada) {
            cargarReporteMovimientos();
        }
    }

    private void cargarReporteMovimientos() {
        // Aquí se implementaría la carga de ventas, avances y vales en la tabla
        System.out.println("Cargando reporte de ventas, avances y vales...");
    }
}
