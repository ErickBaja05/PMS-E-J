package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

public class recuperarPasswordController {

    @FXML private Button butEnviar;
    @FXML private VBox contenedorCentral;
    @FXML private Label lblMensaje; // Usaremos este label para los errores y éxitos
    @FXML private TextField txtCorreo;

    @FXML
    void enviarInstrucciones(ActionEvent event) {
        String correo = txtCorreo.getText().trim();

        // 1. VALIDACIÓN: ¿Está vacío?
        if (correo.isEmpty()) {
            lblMensaje.setText("Por favor, ingrese un correo electrónico.");
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
            return;
        }

        // 2. ESCENARIO 1: Validar formato de correo
        if (!esFormatoValido(correo)) {
            lblMensaje.setText("Formato del correo incorrecto");
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
            return;
        }
        // Para la base de datos
        // 3. ESCENARIO 2: Verificar si está registrado
        if (!estaRegistrado(correo)) {
            lblMensaje.setText("No existe una cuenta asociada a ese correo");
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
            return;
        }

        // 4. ESCENARIO BÁSICO: Envío exitoso
        enviarCorreo(correo);
        lblMensaje.setText("Correo con los datos del usuario enviados correctamente");
        lblMensaje.getStyleClass().remove("mensajeError");
        lblMensaje.getStyleClass().add("mensajeConfirmacion");

        // 5. REGISTRO DE LOGS (Auditoría)
        registrarAccion(correo);
    }

    // --- Métodos de apoyo (Lógica de negocio) ---

    private boolean esFormatoValido(String email) {
        // Validación simple con expresión regular
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean estaRegistrado(String email) {
        // Aquí conectarás con tu Base de Datos más adelante
        // Por ahora simularemos que solo "admin@farmacia.com" existe
        return email.equals("admin@farmacia.com");
    }

    private void enviarCorreo(String email) {
        // Lógica para enviar el usuario y contraseña real (JavaMail)
        System.out.println("Enviando credenciales a: " + email);
    }

    private void registrarAccion(String email) {
        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println("Acción guardada: Recuperación para " + email + " a las " + timestamp);
        // Aquí guardarías en tu tabla de auditoría/logs
    }
}