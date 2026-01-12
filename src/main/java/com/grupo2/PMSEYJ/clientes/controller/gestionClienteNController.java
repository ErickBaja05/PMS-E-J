package com.grupo2.PMSEYJ.clientes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class gestionClienteNController {

    @FXML private Button btnBuscar, btnDarAlta, btnDarBaja, btnModificar;
    @FXML private TextField txtCedula, txtCelular, txtCorreo, txtDireccion,
            txtEstado, txtFechaNacimiento, txtNombre;

    // Valor quemado para pruebas
    private final String CEDULA_TEST = "1234567890";
    private boolean clienteCargado = false;

    // Valores originales para detectar cambios
    private String celularOriginal;
    private String direccionOriginal;
    private String correoOriginal;

    @FXML
    public void initialize() {
        bloquearCampos(true);
        btnBuscar.setDisable(true);

        txtCedula.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() == 10) {
                btnBuscar.setDisable(false);
            } else {
                btnBuscar.setDisable(true);
                limpiarCamposDatos();
                bloquearCampos(true);
                clienteCargado = false;
            }
        });
    }

    // ---------- MÉTODOS DE APOYO ----------
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void registrarLog(String accion) {
        System.out.println("LOG [Módulo Clientes] - " + LocalDateTime.now() +
                ": Acción: " + accion +
                " | Cédula: " + txtCedula.getText() +
                " | Usuario: Admin");
    }

    // ---------- CONSULTAR CLIENTE ----------
    @FXML
    void consultarCliente() {
        String cedula = txtCedula.getText().trim();

        if (cedula.equals(CEDULA_TEST)) {
            txtNombre.setText("Juan Pérez");
            txtFechaNacimiento.setText("1990-01-01");
            txtCorreo.setText("juan@mail.com");
            txtDireccion.setText("Calle Falsa 123");
            txtCelular.setText("0987654321");
            txtEstado.setText("INACTIVO");

            // Guardar valores originales
            celularOriginal = txtCelular.getText();
            direccionOriginal = txtDireccion.getText();
            correoOriginal = txtCorreo.getText();

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
    void darAltaCliente() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        if (txtEstado.getText().equals("ACTIVO")) {
            mostrarAlerta("Este cliente ya fue dado de alta", Alert.AlertType.WARNING);
            return;
        }

        txtEstado.setText("ACTIVO");
        registrarLog("Alta de cliente natural");
        mostrarAlerta("Cliente dado de alta con éxito", Alert.AlertType.INFORMATION);
    }

    // ---------- DAR DE BAJA ----------
    @FXML
    void darBajaCliente() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        if (txtEstado.getText().equals("INACTIVO")) {
            mostrarAlerta("Este cliente ya fue dado de baja", Alert.AlertType.WARNING);
            return;
        }

        txtEstado.setText("INACTIVO");
        registrarLog("Baja de cliente natural");
        mostrarAlerta("Cliente dado de baja con éxito", Alert.AlertType.INFORMATION);
    }

    // ---------- MODIFICAR DATOS ----------
    @FXML
    void modificarDatos() {
        if (!clienteCargado) {
            mostrarAlerta("Cliente no registrado", Alert.AlertType.ERROR);
            return;
        }

        // CASO 7: Celular
        String nuevoCelular = txtCelular.getText().trim();
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

            registrarLog("Modificación de número celular de cliente natural");
            mostrarAlerta("Número celular actualizado correctamente", Alert.AlertType.INFORMATION);
            celularOriginal = nuevoCelular;
            return;
        }

        // CASO 9: Dirección
        String nuevaDireccion = txtDireccion.getText().trim();
        if (!nuevaDireccion.equals(direccionOriginal)) {

            if (nuevaDireccion.isEmpty() || nuevaDireccion.length() > 100) {
                mostrarAlerta("Dirección no válida, la dirección ingresada está vacía o tiene más de 100 caracteres", Alert.AlertType.ERROR);
                return;
            }

            registrarLog("Modificación de dirección de cliente natural");
            mostrarAlerta("Dirección modificada correctamente", Alert.AlertType.INFORMATION);
            direccionOriginal = nuevaDireccion;
            return;
        }

        // CASO 11: Correo
        String nuevoCorreo = txtCorreo.getText().trim();
        if (!nuevoCorreo.equals(correoOriginal)) {

            if (nuevoCorreo.isEmpty() || nuevoCorreo.length() > 50) {
                mostrarAlerta("Correo no válido, el correo ingresado está vacío o tiene más de 50 caracteres", Alert.AlertType.ERROR);
                return;
            }

            registrarLog("Modificación de correo de cliente natural");
            mostrarAlerta("Dirección modificada correctamente", Alert.AlertType.INFORMATION);
            correoOriginal = nuevoCorreo;
            return;
        }

        mostrarAlerta("No se detectaron cambios para modificar", Alert.AlertType.WARNING);
    }

    // ---------- UTILIDADES ----------
    private void bloquearCampos(boolean bloquear) {
        txtCelular.setDisable(bloquear);
        txtCorreo.setDisable(bloquear);
        txtDireccion.setDisable(bloquear);

        btnModificar.setDisable(bloquear);
        btnDarAlta.setDisable(bloquear);
        btnDarBaja.setDisable(bloquear);
    }

    private void limpiarCamposDatos() {
        txtNombre.clear();
        txtFechaNacimiento.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtCelular.clear();
        txtEstado.clear();
    }
}
