package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class crearUserController implements Initializable {

    @FXML
    private ComboBox<String> ComboBTipoUser;

    @FXML
    private Button butCrearUser;

    @FXML
    private VBox contenedorCentral;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cargar tipos de usuario
        ComboBTipoUser.getItems().addAll(
                "Administrador", "Auxiliar"
        );
        ComboBTipoUser.setPromptText("Tipo de usuario");
    }

    @FXML
    void crearUsuario(ActionEvent event) {

        String usuario = txtUsuario.getText();
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        String tipoUsuario = ComboBTipoUser.getValue();

        // ESCENARIO ALTERNATIVO 1: Usuario no válido
        if (usuario == null || usuario.trim().isEmpty()) {
            mostrarError("Nombre de usuario no válido");
            return;
        }

        // ESCENARIO ALTERNATIVO 2: Correo no válido
        if (correo == null || !correo.matches("^.+@.+\\..+$")) {
            mostrarError("Correo electrónico no válido");
            return;
        }

        // ESCENARIO ALTERNATIVO 3: Contraseña no válida
        if (password == null || password.length() < 6) {
            mostrarError("Contraseña no válida (mínimo 6 caracteres)");
            return;
        }

        // Validación adicional: tipo de usuario
        if (tipoUsuario == null) {
            mostrarError("Seleccione el tipo de usuario");
            return;
        }

        // ESCENARIO ALTERNATIVO 4: Usuario o correo existente (simulado)
        boolean existeUsuario = false; // luego irá base de datos

        if (existeUsuario) {
            mostrarError("Usuario o Correo electrónico ya registrados. Ingrese datos diferentes");
            return;
        }

        // ESCENARIO BÁSICO: Usuario creado exitosamente
        LocalDateTime timestamp = LocalDateTime.now();
        String usuarioAccion = "admin"; // simulado

        System.out.println("Usuario creado en: " + timestamp);
        System.out.println("Acción realizada por: " + usuarioAccion);

        mostrarExito("El usuario Administrador fue creado exitosamente.");
        limpiarFormulario();
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(mensaje);
    }

    private void mostrarExito(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: green;");
        lblMensaje.setText(mensaje);
    }

    private void limpiarFormulario() {
        txtUsuario.clear();
        txtCorreo.clear();
        txtPassword.clear();
        ComboBTipoUser.getSelectionModel().clearSelection();
    }
}

