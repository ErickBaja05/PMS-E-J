package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class definirIncentivosYMetasController {

    @FXML private TabPane tabPaneGestion;
    @FXML private TextField txtCodigoProd, txtMetaProd, txtIncentivoProd;
    @FXML private TextField txtNombreLab, txtMetaLab, txtIncentivoLab;

    /**
     * Lanza una ventana emergente para cada tipo de mensaje (Éxito, Error, Advertencia).
     */
    private boolean mostrarVentana(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        if (tipo == Alert.AlertType.CONFIRMATION) {
            ButtonType btnAceptar = new ButtonType("Aceptar");
            ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnAceptar, btnCancelar);
            Optional<ButtonType> result = alert.showAndWait();
            return result.isPresent() && result.get() == btnAceptar;
        } else {
            alert.showAndWait();
            return true;
        }
    }

    @FXML
    void guardarInformacion(ActionEvent event) {
        // Identificar pestaña activa: 0 = Producto, 1 = Laboratorio
        int tabSeleccionada = tabPaneGestion.getSelectionModel().getSelectedIndex();

        if (tabSeleccionada == 0) {
            validarYRegistrarProducto();
        } else {
            validarYRegistrarLaboratorio();
        }
    }

    // --- LÓGICA PARA PRODUCTO (Caso de Uso 1) ---
    private void validarYRegistrarProducto() {
        String codigo = txtCodigoProd.getText().trim();
        String meta = txtMetaProd.getText().trim();
        String incentivo = txtIncentivoProd.getText().trim();

        // 1. Validación de campo vacío (Escenario Alternativo 1)
        if (codigo.isEmpty()) {
            mostrarVentana("Producto No Encontrado", "No existe un producto con el código proporcionado", Alert.AlertType.ERROR);
            return;
        }

        // 2. Validación de Meta (Escenario Alternativo 2)
        if (!esEnteroValido(meta)) {
            mostrarVentana("Error de Validación", "La meta de ventas debe ser un numero entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }

        // 3. Validación de Incentivo (Escenario Alternativo 3)
        if (incentivo.length() <= 5 || incentivo.length() >= 51) {
            mostrarVentana("Longitud Inválida", "El incentivo debe tener más de 5 caracteres y menos de 51", Alert.AlertType.ERROR);
            return;
        }

        // Registro Exitoso con Confirmación
        if (mostrarVentana("Confirmar", "¿Desea guardar esta información?", Alert.AlertType.CONFIRMATION)) {
            mostrarVentana("Éxito", "La información ha sido almacenada correctamente", Alert.AlertType.INFORMATION);
            limpiarCamposProducto();
        }
    }

    // --- LÓGICA PARA LABORATORIO (Caso de Uso 2) ---
    private void validarYRegistrarLaboratorio() {
        String nombre = txtNombreLab.getText().trim();
        String meta = txtMetaLab.getText().trim();
        String incentivo = txtIncentivoLab.getText().trim();

        // 1. Validación de campo vacío (Escenario Alternativo 1)
        if (nombre.isEmpty()) {
            mostrarVentana("Laboratorio No Encontrado", "No existe un Laboratorio con el nombre proporcionado", Alert.AlertType.ERROR);
            return;
        }

        // 2. Validación de Meta (Escenario Alternativo 2)
        if (!esEnteroValido(meta)) {
            mostrarVentana("Error de Validación", "La meta de ventas debe ser un numero entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }

        // 3. Validación de Incentivo (Escenario Alternativo 3)
        if (incentivo.length() <= 5 || incentivo.length() >= 51) {
            mostrarVentana("Longitud Inválida", "El incentivo debe tener más de 5 caracteres y menos de 51", Alert.AlertType.ERROR);
            return;
        }

        // Registro Exitoso con Confirmación
        if (mostrarVentana("Confirmar", "¿Desea guardar esta información?", Alert.AlertType.CONFIRMATION)) {
            mostrarVentana("Éxito", "La información ha sido almacenada correctamente", Alert.AlertType.INFORMATION);
            limpiarCamposLaboratorio();
        }
    }

    @FXML
    void cancelarOperacion(ActionEvent event) {
        if (mostrarVentana("Cancelar", "¿Desea limpiar los campos?", Alert.AlertType.CONFIRMATION)) {
            limpiarCamposProducto();
            limpiarCamposLaboratorio();
        }
    }

    // --- MÉTODOS DE APOYO ---

    private boolean esEnteroValido(String valor) {
        try {
            int n = Integer.parseInt(valor);
            return n > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void limpiarCamposProducto() {
        txtCodigoProd.clear();
        txtMetaProd.clear();
        txtIncentivoProd.clear();
    }

    private void limpiarCamposLaboratorio() {
        txtNombreLab.clear();
        txtMetaLab.clear();
        txtIncentivoLab.clear();
    }
}