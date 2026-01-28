package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.NuevoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioService;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioServiceImpl;
import com.grupo2.PMSEYJ.core.exception.UsuarioYaExisteException;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.javafx.FontIcon;

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
    private PasswordField passFieldPassword;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button verPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Label lblIconoEmail;

    @FXML
    private Label lblIconoUsuario;


    private UsuarioService usuarioService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usuarioService = new UsuarioServiceImpl();
        // Cargar tipos de usuario
        ComboBTipoUser.getItems().addAll(
                "Administrador", "Auxiliar");
        ComboBTipoUser.setPromptText("Tipo de usuario");

        FontIcon icon = new FontIcon("fa-eye");
        icon.getStyleClass().add("botonVer");

        verPassword.setGraphic(icon);

        FontIcon icon2 = new FontIcon("fa-vcard");
        icon2.getStyleClass().add("iconoUsuario");

        FontIcon icon3 = new FontIcon("fa-envelope");
        icon3.getStyleClass().add("iconoEmail");

        lblIconoUsuario.setText(null);
        lblIconoUsuario.setGraphic(icon2);

        lblIconoEmail.setText(null);
        lblIconoEmail.setGraphic(icon3);

        // Sincronizar contenido
        textFieldPassword.textProperty().bindBidirectional(passFieldPassword.textProperty());
        textFieldPassword.setManaged(false);
        textFieldPassword.setVisible(false);

        // Acción del ojito
        verPassword.setOnAction(e -> {
            if (textFieldPassword.isVisible()) {
                textFieldPassword.setVisible(false);
                textFieldPassword.setManaged(false);
                passFieldPassword.setVisible(true);
                passFieldPassword.setManaged(true);
            } else {
                textFieldPassword.setVisible(true);
                textFieldPassword.setManaged(true);
                passFieldPassword.setVisible(false);
                passFieldPassword.setManaged(false);
            }
        });
    }

    @FXML
    void crearUsuario(ActionEvent event) {

        String usuario = txtUsuario.getText();
        String correo = txtCorreo.getText();
        String password = passFieldPassword.getText();
        String tipoUsuario = ComboBTipoUser.getSelectionModel().getSelectedItem();
        String perfil;

        // PARSING A LO VALORES DE TIPO DE USUARIO

        // Validación adicional: tipo de usuario
        if (tipoUsuario == null) {
            mostrarError("Seleccione el tipo de usuario");
            return;
        }

        if (tipoUsuario.equals("Administrador")) {
            perfil = "AD";
        } else {
            perfil = "VE";
        }

        // ESCENARIO ALTERNATIVO 1: nombreUsuario no cumple longitud
        if (usuario == null || usuario.trim().isEmpty() ||
                usuario.length() < 10 || usuario.length() >= 51) {

            mostrarError("El usuario debe tener entre 10 y 50 caracteres ");
            return;
        }

        // ESCENARIO ALTERNATIVO 2: Correo no válido
        if (correo == null || correo.trim().isEmpty()) {
            mostrarError("El correo electrónico  no puede estar vacio");
            return;
        }

        if (!correo.matches("^.+@.+\\..+$")) {
            mostrarError("Correo electrónico no válido");
            return;
        }

        // ESCENARIO ALTERNATIVO 3: Contraseña no válida
        if (password == null || password.trim().isEmpty()) {
            mostrarError("Debe proporcionar una contraseña");
            return;
        }

        // Expresión regular para:
        // 8 a 16 caracteres
        // al menos una minúscula
        // al menos una mayúscula
        // al menos un carácter especial
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,16}$";

        if (!password.matches(regexPassword)) {
            mostrarError(
                    "La contraseña debe tener de entre 8 y 16 caracteres con al menos una minúscula, una mayúscula y un carácter especial");
            return;
        }

        NuevoUsuarioDTO nuevoUsuario = new NuevoUsuarioDTO();
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setNombre(usuario);
        nuevoUsuario.setPerfil(perfil);

        try {
            usuarioService.insertarUsuario(nuevoUsuario);
            LocalDateTime timestamp = LocalDateTime.now();
            String usuarioAccion = SesionActual.getUsuario().getNombre_us();

            System.out.println("Usuario creado en: " + timestamp);
            System.out.println("Acción realizada por: " + usuarioAccion);

            mostrarExito("El usuario " + nuevoUsuario.getNombre() + " se ha registrado correctamente");
            limpiarFormulario();
        } catch (UsuarioYaExisteException e) {
            mostrarError(e.getMessage());
        }
        // ESCENARIO ALTERNATIVO 4: Usuario o correo existente (simulado)

    }

    private void mostrarError(String mensaje) {
        lblMensaje.setTextAlignment(TextAlignment.CENTER);
        lblMensaje.setAlignment(Pos.CENTER);
        lblMensaje.getStyleClass().setAll("mensajeError");
        lblMensaje.setText(mensaje);
    }

    private void mostrarExito(String mensaje) {
        lblMensaje.setTextAlignment(TextAlignment.CENTER);
        lblMensaje.setAlignment(Pos.CENTER);
        lblMensaje.getStyleClass().setAll("mensajeConfirmacion");
        lblMensaje.setText(mensaje);
    }

    private void limpiarFormulario() {
        txtUsuario.clear();
        txtCorreo.clear();
        passFieldPassword.clear();
        textFieldPassword.clear();
        ComboBTipoUser.getSelectionModel().clearSelection();
    }
}
