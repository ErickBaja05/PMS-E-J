package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class realizarAvanceDeCajaController {

    @FXML private TextArea txtMotivo;
    @FXML private TextField txtMontoSolicitado;

    // Simulación de variables de estado y saldo del sistema
    private boolean esCajaCerrada = false;
    private double saldoDisponible = 500.00; // Ejemplo de saldo actual en caja

    /**
     * Método genérico para mostrar las alertas solicitadas en los casos de uso.
     */
    private void mostrarAlerta(String titulo, String msj, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    @FXML
    void handleGenerarAvance(ActionEvent event) {
        // 1. Verificar estado de la Caja (Escenario Básico Paso 1)
        if (esCajaCerrada) {
            // ESCENARIO ALTERNATIVO 1: Caja cerrada
            mostrarAlerta("Error", "Caja cerrada, no se permiten vales de caja.", Alert.AlertType.ERROR);
            return;
        }

        String montoS = txtMontoSolicitado.getText().trim();
        String motivo = txtMotivo.getText().trim();

        // 2. Validar que el monto sea un número decimal (Paso 3)
        try {
            double monto = Double.parseDouble(montoS);

            // 3. Validar que el monto sea mayor que 0 (Paso 4)
            if (monto <= 0) {
                // ESCENARIO ALTERNATIVO 3
                mostrarAlerta("Monto Inválido", "Monto no válido", Alert.AlertType.ERROR);
                return;
            }

            // 4. Comparar montoSolicitado con saldoDisponible (Paso 5)
            if (monto >= saldoDisponible) {
                // ESCENARIO ALTERNATIVO 4: Saldo insuficiente
                mostrarAlerta("Saldo Insuficiente", "Saldo insuficiente", Alert.AlertType.WARNING);
                return;
            }

            // 5. Validar ingreso de motivo (Paso 6)
            if (motivo.isEmpty()) {
                mostrarAlerta("Dato Requerido", "Por favor, ingrese el motivo del avance.", Alert.AlertType.WARNING);
                return;
            }

            // 6. Proceso Exitoso (Paso 7-9)
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Avance");
            confirmacion.setContentText("¿Confirma la salida de $" + monto + " de la caja?");

            Optional<ButtonType> result = confirmacion.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // El sistema resta el monto y registra la acción
                saldoDisponible -= monto;
                mostrarAlerta("Éxito", "Avance de caja realizado exitosamente", Alert.AlertType.INFORMATION);
                limpiarFormulario();
            }

        } catch (NumberFormatException e) {
            // ESCENARIO ALTERNATIVO 2: No es decimal
            mostrarAlerta("Monto Inválido", "Monto no válido", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleCancelar(ActionEvent event) {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtMontoSolicitado.clear();
        txtMotivo.clear();
    }
}
