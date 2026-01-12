package com.grupo2.PMSEYJ.clientes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class gestionClienteJController {

    @FXML private Button btnBuscar, btnDarAltaJ, btnDarBajaJ, btnModificarJ;
    @FXML private TextField txtRuc, txtCelularJ, txtCorreoJ, txtDireccionJ,
            txtEstado, txtNombre;

    // RUC de prueba
    private final String RUC_TEST = "1790012345001";
    private boolean clienteCargado = false;

    // Valores originales
    private String celularOriginal;
    private String direccionOriginal;
    private String correoOriginal;

    @FXML
    public void initialize() {
        bloquearCampos(true);
        btnBuscar.setDisable(true);

        txtRuc.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() == 13) {
                btnBuscar.setDisable(false);
            } else {
                btnBuscar.setDisable(true);
                limpiarCamposDatos();
                bloquearCampos(true);
                clienteCargado = false;
            }
        });
    }

    // ---------- UTILIDADES ----------
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void registrarLog(String accion) {
        System.out.println("LOG [Módulo Clientes] - " + LocalDateTime.now() +
                ": Acción: " + accion +
                " | RUC: " + txtRuc.getText() +
                " | Usuario: Admin");
    }

    // ---------- CONSULTAR CLIENTE ----------
    @FXML
    void consultarClienteJ() {
        String ruc = txtRuc.getText().trim();

        if (ruc.equals(RUC_TEST)) {
            txtNombre.setText("Empresa Farmacéutica S.A.");
            txtCorreoJ.setText("contacto@empresa.com");
            txtDireccionJ.setText("Av. Amazonas y Naciones Unidas");
            txtCelularJ.setText("0991234567");
            txtEstado.setText("INACTIVO");

            // Guardar valores originales
            celularOriginal = txtCelularJ.getText();
            direccionOriginal = txtDireccionJ.getText();
            correoOriginal = txtCorreoJ.getText();

            bloquearCampos(false);
            clienteCargado = true;
        } else {
            limpiarCamposDatos();
            bloquearCampos(true);
            clienteCargado = false;
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
        }
    }

    // ---------- DAR DE ALTA ----------
    @FXML
    void darAltaClienteJ() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        if (txtEstado.getText().equals("ACTIVO")) {
            mostrarAlerta("Este cliente ya fue dado de alta", Alert.AlertType.WARNING);
            return;
        }

        txtEstado.setText("ACTIVO");
        registrarLog("Alta de cliente jurídico");
        mostrarAlerta("Cliente dado de alta con éxito", Alert.AlertType.INFORMATION);
    }

    // ---------- DAR DE BAJA ----------
    @FXML
    void darBajaClienteJ() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        if (txtEstado.getText().equals("INACTIVO")) {
            mostrarAlerta("Este cliente ya fue dado de baja", Alert.AlertType.WARNING);
            return;
        }

        txtEstado.setText("INACTIVO");
        registrarLog("Baja de cliente jurídico");
        mostrarAlerta("Cliente dado de baja con éxito", Alert.AlertType.INFORMATION);
    }

    // ---------- MODIFICAR DATOS ----------
    @FXML
    void modificarDatosJ() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        // CASO: MODIFICAR CELULAR
        String nuevoCelular = txtCelularJ.getText().trim();
        if (!nuevoCelular.equals(celularOriginal)) {

            if (nuevoCelular.length() != 10) {
                mostrarAlerta("Número celular no válido, el número ingresado no tiene 10 dígitos", Alert.AlertType.ERROR);
                return;
            }
            if (!nuevoCelular.matches("[0-9]+")) {
                mostrarAlerta("Número celular no válido, el número ingresado contiene caracteres no permitidos", Alert.AlertType.ERROR);
                return;
            }
            if (!nuevoCelular.startsWith("09")) {
                mostrarAlerta("Número celular no válido, el número ingresado con comienza con 09", Alert.AlertType.ERROR);
                return;
            }

            registrarLog("Modificación de número celular de cliente jurídico");
            mostrarAlerta("Número celular actualizado correctamente", Alert.AlertType.INFORMATION);
            celularOriginal = nuevoCelular;
            return;
        }

        // CASO: MODIFICAR DIRECCIÓN
        String nuevaDireccion = txtDireccionJ.getText().trim();
        if (!nuevaDireccion.equals(direccionOriginal)) {

            if (nuevaDireccion.isEmpty() || nuevaDireccion.length() > 100) {
                mostrarAlerta("Dirección no válida, la dirección ingresada está vacía o tiene más de 100 caracteres", Alert.AlertType.ERROR);
                return;
            }

            registrarLog("Modificación de dirección de cliente jurídico");
            mostrarAlerta("Dirección modificada correctamente", Alert.AlertType.INFORMATION);
            direccionOriginal = nuevaDireccion;
            return;
        }

        // CASO: MODIFICAR CORREO
        String nuevoCorreo = txtCorreoJ.getText().trim();
        if (!nuevoCorreo.equals(correoOriginal)) {

            if (nuevoCorreo.isEmpty() || nuevoCorreo.length() > 50) {
                mostrarAlerta("Correo no válido, el correo ingresado está vacío o tiene más de 50 caracteres", Alert.AlertType.ERROR);
                return;
            }

            registrarLog("Modificación de correo de cliente jurídico");
            mostrarAlerta("Dirección modificada correctamente", Alert.AlertType.INFORMATION);
            correoOriginal = nuevoCorreo;
            return;
        }

        mostrarAlerta("No se detectaron cambios para modificar", Alert.AlertType.WARNING);
    }

    // ---------- INTERFAZ ----------
    private void bloquearCampos(boolean bloquear) {
        txtCelularJ.setDisable(bloquear);
        txtCorreoJ.setDisable(bloquear);
        txtDireccionJ.setDisable(bloquear);

        btnModificarJ.setDisable(bloquear);
        btnDarAltaJ.setDisable(bloquear);
        btnDarBajaJ.setDisable(bloquear);
    }

    private void limpiarCamposDatos() {
        txtNombre.clear();
        txtCorreoJ.clear();
        txtDireccionJ.clear();
        txtCelularJ.clear();
        txtEstado.clear();
    }
}
