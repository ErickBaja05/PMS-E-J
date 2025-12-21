package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class realizarValeDeCajaController {

    @FXML private TextArea txtMotivo;
    @FXML private TextField txtMontoSolicitado;

    // Simulación de datos del sistema
    private boolean cajaAbierta = true;
    private double saldoDisponible = 100.00;

    private void mostrarAlerta(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    @FXML
    void handleGenerarVale(ActionEvent event) {
        // 1. Verificar estado de la Caja
        if (!cajaAbierta) {
            // ESCENARIO ALTERNATIVO 1
            mostrarAlerta("Caja Cerrada", "Caja cerrada, no se permiten vales de caja.", Alert.AlertType.ERROR);
            return;
        }

        String montoS = txtMontoSolicitado.getText().trim();
        String motivo = txtMotivo.getText().trim();

        // 2. Validar número decimal
        try {
            double monto = Double.parseDouble(montoS);

            // 3. Validar mayor que 0
            if (monto <= 0) {
                // ESCENARIO ALTERNATIVO 3
                mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
                return;
            }

            // 4. Comparar con saldoDisponible
            if (monto >= saldoDisponible) {
                // ESCENARIO ALTERNATIVO 4
                mostrarAlerta("Saldo Insuficiente", "Saldo insuficiente", Alert.AlertType.WARNING);
                return;
            }

            // 5. Validar ingreso de motivo
            if (motivo.isEmpty()) {
                mostrarAlerta("Faltan Datos", "Debe ingresar el motivo para el vale.", Alert.AlertType.WARNING);
                return;
            }

            // 6. Proceso Exitoso
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setContentText("¿Desea realizar el vale por $" + monto + "?");
            Optional<ButtonType> result = confirmacion.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                saldoDisponible -= monto;
                mostrarAlerta("Éxito", "Vale de caja realizado exitosamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            // ESCENARIO ALTERNATIVO 2
            mostrarAlerta("Error", "Monto no válido", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleCancelar(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtMontoSolicitado.clear();
        txtMotivo.clear();
    }
}