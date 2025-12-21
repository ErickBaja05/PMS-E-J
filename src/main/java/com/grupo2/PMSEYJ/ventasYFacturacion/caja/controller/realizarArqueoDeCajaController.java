package com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class realizarArqueoDeCajaController implements Initializable {

    @FXML private Label lblSaldoDisponible;
    @FXML private TextField txtValorFisico;

    // Simulación de saldo en el sistema (Paso 1 del Escenario Básico)
    private double saldoSistema = 1250.75;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Al iniciar, el sistema muestra el saldoDisponible
        lblSaldoDisponible.setText("$ " + String.format("%.2f", saldoSistema));
    }

    /**
     * Sistema de notificaciones por ventanas emergentes.
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void handleRealizarArqueo(ActionEvent event) {
        String inputFisico = txtValorFisico.getText().trim();

        // 1. Validar que sea un número decimal
        try {
            double valorFisico = Double.parseDouble(inputFisico);

            // 2. Validar que el valor físico sea mayor que 0
            if (valorFisico <= 0) {
                // ESCENARIO ALTERNATIVO 2: No permite negativos o cero
                mostrarAlerta("Valor No Válido",
                        "No se permiten números negativos, ingrese un valor valido",
                        Alert.AlertType.ERROR);
                return;
            }

            // 3. Verificar coincidencia entre saldoSistema y valorFisico
            if (Math.abs(saldoSistema - valorFisico) < 0.001) {
                // ESCENARIO ALTERNATIVO 3: Coinciden plenamente
                mostrarAlerta("Resultado de Arqueo",
                        "No hubo diferencias en la caja",
                        Alert.AlertType.INFORMATION);
            } else {
                // ESCENARIO BÁSICO: Existen diferencias (Sobran o faltan)
                mostrarAlerta("Resultado de Arqueo",
                        "Hubo diferencias en la caja",
                        Alert.AlertType.WARNING);
                // Aquí el sistema registraría internamente el monto de la diferencia
            }

            txtValorFisico.clear();

        } catch (NumberFormatException e) {
            // ESCENARIO ALTERNATIVO 1: No es un número decimal válido
            mostrarAlerta("Error de Formato",
                    "Ingrese un valor decimal valido",
                    Alert.AlertType.ERROR);
        }
    }
}
