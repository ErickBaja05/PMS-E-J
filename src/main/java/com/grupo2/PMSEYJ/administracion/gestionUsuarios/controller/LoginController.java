package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.UsuarioSesionDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioService;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioServiceImpl;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button botonLogin;

    @FXML
    private Button btnPassOlvi;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUsername;

    private UsuarioService usuarioService;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        try {
            UsuarioSesionDTO sesion = usuarioService.login(
                    textFieldUsername.getText(),
                    textFieldPassword.getText()
            );

            SesionActual.iniciarSesion(sesion);

            mostrarInfo("Bienvenido",
                    "Inicio de sesión exitoso",
                    "Bienvenido al sistema PMS-E&J, " + sesion.getNombre_us()
            );

            String vista = switch (sesion.getPerfil_us()) {
                case "AU" -> "/auditoria/fxml/ventanaDeAuditoria.fxml";
                default -> "/administracion/fxml/ventanaPrincipal.fxml";
            };

            Parent root = FXMLLoader.load(getClass().getResource(vista));
            NavigationUtil.changeScene(event, root);

        } catch (IllegalArgumentException e) {
            mostrarError("Error de autenticación", e.getMessage());
        } catch (Exception e) {
            mostrarError("Error del sistema",
                    "Ocurrió un error inesperado. Contacte al administrador.");
            e.printStackTrace();
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String header, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }




    @FXML
    void recuperarContraseña(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/administracion/fxml/recuperarPassword.fxml"));
        Parent root = loader.load();
        NavigationUtil.openNewWindow(event,root,"Recuperar Password");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioService = new UsuarioServiceImpl();

    }
}
