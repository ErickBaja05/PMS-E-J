package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class recuperarPasswordController implements Initializable {

    // =========================
    // CONTROLES FXML
    // =========================
    @FXML private TextField txtCorreo;
    @FXML private Button btnCancelar;
    @FXML private Button butEnviar;

    // =========================
    // INITIALIZE
    // =========================
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // No se requiere inicialización especial
    }

    // =========================
    // ENVIAR INSTRUCCIONES
    // =========================
    @FXML
    void enviarInstrucciones(ActionEvent event) {

        String correo = txtCorreo.getText().trim();

        // 1. Validación campo vacío
        if (correo.isEmpty()) {
            mostrarAlerta(
                    "Error",
                    "Debe ingresar un correo electrónico",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // 2. Validación formato correo
        if (!correoValido(correo)) {
            mostrarAlerta(
                    "Error",
                    "Correo electrónico no válido",
                    Alert.AlertType.ERROR
            );
            return;
        }

        // 3. Verificar existencia del correo
        boolean existe = verificarCorreoEnDB(correo);

        if (!existe) {
            // ESCENARIO ALTERNATIVO
            mostrarAlerta(
                    "Error",
                    "No existe un usuario con el correo proporcionado",
                    Alert.AlertType.ERROR
            );
            return;
        }

        // ESCENARIO BÁSICO
        enviarCorreoConCredenciales(correo);

        mostrarAlerta(
                "Éxito",
                "Correo con los datos del usuario enviados correctamente",
                Alert.AlertType.INFORMATION
        );

        registrarAuditoria(correo);

        txtCorreo.clear();
    }

    // =========================
    // CANCELAR
    // =========================
    @FXML
    void cancelarRecuperacion(ActionEvent event) {
        txtCorreo.clear();
    }

    // =========================
    // VALIDACIONES
    // =========================
    private boolean correoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, correo);
    }

    // =========================
    // SIMULACIÓN BASE DE DATOS
    // =========================
    private boolean verificarCorreoEnDB(String correo) {
        // Simulación: solo existe este correo
        return correo.equalsIgnoreCase("admin@farmacia.com")
                || correo.equalsIgnoreCase("auxiliar@farmacia.com");
    }

    private void enviarCorreoConCredenciales(String correo) {
        // Simulación de envío de correo
        System.out.println("Enviando credenciales al correo: " + correo);
    }

    // =========================
    // AUDITORÍA
    // =========================
    private void registrarAuditoria(String correo) {

        String usuarioActual = "adminActual";
        LocalDateTime fechaHora = LocalDateTime.now();

        System.out.println("MÓDULO: Administración");
        System.out.println("ACCIÓN: Recuperación de contraseña por correo");
        System.out.println("FECHA/HORA: " + fechaHora);
        System.out.println("CORREO INGRESADO: " + correo);
        System.out.println("REALIZADO POR: " + usuarioActual);
    }

    // =========================
    // ALERTAS
    // =========================
    private void mostrarAlerta(String titulo,
                               String mensaje,
                               Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
