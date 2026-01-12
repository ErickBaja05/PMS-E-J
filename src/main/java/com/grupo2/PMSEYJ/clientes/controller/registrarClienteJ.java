package com.grupo2.PMSEYJ.clientes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class registrarClienteJ {

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCedulaJ;

    @FXML
    private TextField txtCelularJ;

    @FXML
    private TextField txtCorreoJ;

    @FXML
    private TextField txtDireccionJ;

    @FXML
    private TextField txtNombre;

    @FXML
    void registrarCliente(ActionEvent event) {
        // VALIDACIONES A NIVEL DE INTERFAZ DE USUARIO, FORMATOS Y LONGITUDES


        // SE DEBE PROPORCIONAR TODOS LOS DATOS
        if (txtCedulaJ.getText().isEmpty() || txtCelularJ.getText().isEmpty() || txtCorreoJ.getText().isEmpty() || txtDireccionJ.getText().isEmpty()  || txtNombre.getText().isEmpty()) {
            mostrarMensaje("Debe ingresar todos los datos para registar a un cliente", true);
            return;
        }

        // 13 DÍGITOS PARA EL RUC
        if (!(txtCedulaJ.getText().matches("[0-9]{13}"))) {
            mostrarMensaje("El RUC debe ser solo números y deben ser 13 digitos", true);
            return;
        }

        // 10 DÍGITOS PARA EL CELULAR

        if (!(txtCelularJ.getText().matches("[0-9]{10}"))) {
            mostrarMensaje("El numero de telefono es incorrecto, ingrese solo números y que sean 10", true);
            return;
        }

        // HASTA 100 DIGITOS PARA EL CORREO

        if (txtCorreoJ.getText().length() > 100) {
            mostrarMensaje("El correo no debe superar los 100 caracteres", true);
            return;
        }

        // HASTA 200 CARACTERES PARA LA DIRECCION

        if (txtDireccionJ.getText().length() > 100) {
            mostrarMensaje("La dirección no debe superar los 200 caracteres", true);
            return;
        }

        // HASTA 100 CARACTERES PARA EL NOMBRE

        if (txtNombre.getText().length() > 100) {
            mostrarMensaje("La razón social no debe superar los 100 caracteres", true);
            return;
        }

        // SE CUMPLIERON TODAS LAS VALIDACIONES
        mostrarMensaje("Cliente registrado exitosamente", false);
    }

    private void mostrarMensaje(String texto, boolean esError) {

        if(esError) {
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
        }else{
            lblMensaje.getStyleClass().remove("mensajeError");
            lblMensaje.getStyleClass().add("mensajeConfirmacion");
        }
        lblMensaje.setText(texto);
    }

}
