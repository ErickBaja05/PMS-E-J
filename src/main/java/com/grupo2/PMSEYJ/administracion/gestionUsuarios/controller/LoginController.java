package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button botonLogin;

    @FXML
    private Button btnPassOlvi;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUsername;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {

        if (textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos vacios");
            alert.setHeaderText("Error: Datos vacios");
            alert.setContentText("El usuario o contraseña estan vacios. Debe ingresar todos los campos para continuar");
            alert.showAndWait();

        }else{

            if(textFieldUsername.getText().equals("audit") && textFieldPassword.getText().equals("12345")){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login para auditoria");
                alert.setHeaderText("Ingreso al módulo de auditoria");
                alert.setContentText("Bienvenido al Sistema PMS-E&J");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/auditoria/fxml/ventanaDeAuditoria.fxml"));
                Parent root = loader.load();
                NavigationUtil.changeScene(event,root);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login exitoso");
                alert.setHeaderText("Ingreso exitoso al sistema");
                alert.setContentText("Bienvenido al Sistema PMS-E&J");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/administracion/fxml/ventanaPrincipal.fxml"));
                Parent root = loader.load();
                NavigationUtil.changeScene(event,root);
            }

        }

    }

    @FXML
    void recuperarContraseña(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/administracion/fxml/recuperarPassword.fxml"));
        Parent root = loader.load();
        NavigationUtil.openNewWindow(event,root,"Recuperar Password");

    }

}
