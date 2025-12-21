package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class configuracionPagosImpuestosController {

    @FXML private Button btnDeshabilitar, btnHabilitar, btnRegistrarIVA;
    @FXML private TableColumn<?, ?> colEstado, colNombre;
    @FXML private TableView<?> tablaMetodosPago;
    @FXML private TextField txtNombrePago, txtValorIVA;

    // --- MÉTODOS DE APOYO PARA VENTANAS EMERGENTES ---

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean confirmarAccion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType btnAceptar = new ButtonType("Aceptar");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnAceptar, btnCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == btnAceptar;
    }

    // --- CASO DE USO: definirValorDeIVA ---

    @FXML
    void definirIVA(ActionEvent event) {
        String inputIVA = txtValorIVA.getText().trim();

        if (inputIVA.isEmpty()) {
            mostrarAlerta("Campo Vacío", "Por favor, ingrese un valor para el IVA.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double valorIVA = Double.parseDouble(inputIVA);

            // Validación de Escenario Básico y Alternativo 1
            if (valorIVA > 0 && valorIVA <= 100) {
                if (confirmarAccion("Confirmar IVA", "¿Desea establecer el IVA en " + valorIVA + "%?")) {
                    // Aquí se conectaría con la lógica compleja para registrar el valor
                    mostrarAlerta("Éxito", "Valor del IVA registrado correctamente", Alert.AlertType.INFORMATION);
                    txtValorIVA.clear();
                }
            } else {
                mostrarAlerta("Valor no válido", "El valor del IVA debe ser un numero decimal mayor que 0 y menor o igual a 100", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El valor ingresado no es un número decimal válido.", Alert.AlertType.ERROR);
        }
    }

    // --- CASO DE USO: habilitarMétodosDePago ---

    @FXML
    void habilitarMetodo(ActionEvent event) {
        String nombrePago = txtNombrePago.getText().trim();

        if (nombrePago.isEmpty()) {
            mostrarAlerta("Campo Vacío", "Debe ingresar el nombre del método de pago a habilitar.", Alert.AlertType.WARNING);
            return;
        }

        if (confirmarAccion("Confirmar Habilitación", "¿Desea habilitar el método de pago: " + nombrePago + "?")) {
            // Simulación de verificación de existencia (Escenario Alternativo 1)
            boolean existeMetodo = verificarExistenciaSimulada(nombrePago);

            if (existeMetodo) {
                mostrarAlerta("Éxito", "Método de pago habilitado exitosamente", Alert.AlertType.INFORMATION);
                txtNombrePago.clear();
            } else {
                mostrarAlerta("Error", "El método de pago no existe", Alert.AlertType.ERROR);
            }
        }
    }

    // --- CASO DE USO: deshabilitarMétodosDePago ---

    @FXML
    void deshabilitarMetodo(ActionEvent event) {
        String nombrePago = txtNombrePago.getText().trim();

        if (nombrePago.isEmpty()) {
            mostrarAlerta("Campo Vacío", "Debe ingresar el nombre del método de pago a deshabilitar.", Alert.AlertType.WARNING);
            return;
        }

        if (confirmarAccion("Confirmar Deshabilitación", "¿Desea deshabilitar el método de pago: " + nombrePago + "?")) {
            // Simulación de verificación de existencia (Escenario Alternativo 1)
            boolean existeMetodo = verificarExistenciaSimulada(nombrePago);

            if (existeMetodo) {
                mostrarAlerta("Éxito", "Método de pago deshabilitado exitosamente", Alert.AlertType.INFORMATION);
                txtNombrePago.clear();
            } else {
                mostrarAlerta("Error", "El método de pago no existe", Alert.AlertType.ERROR);
            }
        }
    }

    // --- LÓGICA DE SIMULACIÓN (Para pruebas de interfaz) ---

    private boolean verificarExistenciaSimulada(String nombre) {
        // Simulación: solo reconoce "Efectivo" y "Tarjeta" como existentes
        return nombre.equalsIgnoreCase("Efectivo") || nombre.equalsIgnoreCase("Tarjeta");
    }
}