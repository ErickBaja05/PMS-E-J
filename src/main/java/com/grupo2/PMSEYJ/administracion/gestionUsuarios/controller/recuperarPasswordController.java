package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dao.UsuarioDAO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.InfoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioService;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class recuperarPasswordController implements Initializable {

    @FXML private Button butEnviar;
    @FXML private VBox contenedorCentral;
    @FXML private Label lblMensaje; // Usaremos este label para los errores y éxitos
    @FXML private TextField txtCorreo;

    private UsuarioService usuarioService;
    private UsuarioDAO usuarioDAO;

    @FXML
    void enviarInstrucciones(ActionEvent event) {
        String correo = txtCorreo.getText().trim();

        // 1. VALIDACIÓN: ¿Está vacío?
        if (correo.isEmpty()) {
            lblMensaje.setText("El correo electrónico no puede estar vacio.");
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
            return;
        }

        // 2. ESCENARIO 1: Validar formato de correo
        if (!esFormatoValido(correo)) {
            lblMensaje.setText("El correo electrónico no tiene formato válido");
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
        return email.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean estaRegistrado(String email) {
        // Aquí conectarás con tu Base de Datos más adelante
        // Por ahora simularemos que solo "admin@farmacia.com" existe
        try{
            InfoUsuarioDTO usuario = usuarioService.consultarUsuarioPorEmail(email);
            return usuario != null;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
        return false;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioService = new UsuarioServiceImpl();
    }
}