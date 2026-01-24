package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.NuevoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioService;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioServiceImpl;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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

    private UsuarioService usuarioService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioService = new UsuarioServiceImpl();
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
        String tipoUsuario = ComboBTipoUser.getSelectionModel().getSelectedItem();
        String perfil;

        // PARSING A LO VALORES DE TIPO DE USUARIO



        // Validación adicional: tipo de usuario
        if (tipoUsuario == null) {
            mostrarError("Seleccione el tipo de usuario");
            return;
        }

        if(tipoUsuario.equals("Administrador")){
            perfil = "AD";
        }else{
            perfil = "VE";
        }

        // ESCENARIO ALTERNATIVO 1: Usuario no válido
        if (usuario == null || usuario.trim().isEmpty()) {
            mostrarError("El nombre de usuario no puede estar vacio");
            return;
        }

        // ESCENARIO ALTERNATIVO 2: Correo no válido
        if (correo == null || correo.trim().isEmpty()) {
            mostrarError("El correo electrónico  no puede estar vacio");
            return;
        }

        if(!correo.matches("^.+@.+\\..+$"))
        {
            mostrarError("Correo electrónico no válido");
            return;
        }

        // ESCENARIO ALTERNATIVO 3: Contraseña no válida
        if (password == null || password.trim().isEmpty()) {
            mostrarError("Debe proporcionar una contraseña");
            return;
        }

        if (password.length() < 6) {
            mostrarError("Contraseña no válida (mínimo 6 caracteres)");
            return;
        }

        NuevoUsuarioDTO nuevoUsuario = new NuevoUsuarioDTO();
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setNombre(usuario);
        nuevoUsuario.setPerfil(perfil);

        // ESCENARIO ALTERNATIVO 4: Usuario o correo existente (simulado)
        boolean sePudoInsertar = usuarioService.insertarUsuario(nuevoUsuario);


        if (!sePudoInsertar) {
            mostrarError("Usuario o Correo electrónico ya registrados. Ingrese datos diferentes");
        }else{
            LocalDateTime timestamp = LocalDateTime.now();
            String usuarioAccion = SesionActual.getUsuario().getNombre_us();

            System.out.println("Usuario creado en: " + timestamp);
            System.out.println("Acción realizada por: " + usuarioAccion);

            mostrarExito("El usuario " + nuevoUsuario.getNombre() + " se ha registrado correctamente");
            limpiarFormulario();
        }



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
        txtPassword.clear();
        ComboBTipoUser.getSelectionModel().clearSelection();
    }
}

