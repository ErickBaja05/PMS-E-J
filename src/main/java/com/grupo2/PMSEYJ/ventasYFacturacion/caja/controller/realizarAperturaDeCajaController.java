package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class realizarAperturaDeCajaController {

    @FXML private TextField txtSaldoDisponible;
    @FXML private Label lblEstadoCaja;

    // Simulación del estado del sistema
    private boolean esCajaAbierta = false;

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean confirmarAccion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Acción");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    void handleAbrirCaja(ActionEvent event) {
        // 1. Verificar estado de la Caja
        if (esCajaAbierta) {
            // Escenario Alternativo 1
            mostrarAlerta("Error", "La caja ya se encuentra abierta", Alert.AlertType.WARNING);
            return;
        }

        String saldoStr = txtSaldoDisponible.getText().trim();

        // 2. Validar que el saldoDisponible sea un número decimal
        try {
            double saldo = Double.parseDouble(saldoStr);

            // 3. Validar que el saldoDisponible sea mayor que 0
            if (saldo > 0) {
                if (confirmarAccion("¿Desea realizar la apertura de caja con $" + saldo + "?")) {
                    // Escenario Básico: Registro y cambio de estado
                    esCajaAbierta = true;
                    lblEstadoCaja.setText("Caja actualmente: ABIERTA");
                    mostrarAlerta("Éxito", "Caja abierta exitosamente", Alert.AlertType.INFORMATION); //
                    txtSaldoDisponible.clear();
                }
            } else {
                // Escenario Alternativo 3
                mostrarAlerta("Monto no válido", "Monto no válido", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            // Escenario Alternativo 2
            mostrarAlerta("Monto no válido", "Monto no válido", Alert.AlertType.ERROR);
        }
    }
}
