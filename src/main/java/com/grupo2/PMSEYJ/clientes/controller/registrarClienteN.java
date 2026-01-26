package com.grupo2.PMSEYJ.clientes.controller;

import com.grupo2.PMSEYJ.clientes.dto.NuevoClienteNaturalDTO;
import com.grupo2.PMSEYJ.clientes.service.ClienteNaturalService;
import com.grupo2.PMSEYJ.clientes.service.ClienteNaturalServiceImpl;
import com.grupo2.PMSEYJ.core.exception.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class registrarClienteN implements Initializable {

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtNombre;

    private ClienteNaturalService clienteNaturalService;

    @FXML
    void registrarCliente(ActionEvent event) {

        // VALIDACIONES A NIVEL DE INTERFAZ DE USUARIO, FORMATOS Y LONGITUDES


        // SE DEBE PROPORCIONAR TODOS LOS DATOS
        if(txtCedula.getText().isEmpty() || txtCelular.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtFechaNacimiento.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            mostrarMensaje("Debe ingresar todos los datos para registar a un cliente",true);
            return;
        }

        // 10 DÍGITOS PARA LA CÉDULA
        if (!(txtCedula.getText().matches("[0-9]{10}"))){
            mostrarMensaje("La cédula de identidad deben ser solo números y deben ser 10 digitos",true);
            return;
        }

        // 10 DÍGITOS PARA EL CELULAR

        if (!(txtCelular.getText().matches("[0-9]{10}"))){
            mostrarMensaje("El numero de telefono es incorrecto, ingrese solo números y que sean 10", true);
            return;
        }

        // HASTA 100 DIGITOS PARA EL CORREO

        if(txtCorreo.getText().length() > 100){
            mostrarMensaje("El correo no debe superar los 100 caracteres",true);
            return;
        }

        // HASTA 200 CARACTERES PARA LA DIRECCION

        if(txtDireccion.getText().length() > 100){
            mostrarMensaje("La dirección no debe superar los 200 caracteres",true);
            return;
        }

        // HASTA 100 CARACTERES PARA EL NOMBRE

        if(txtNombre.getText().length() > 100){
            mostrarMensaje("El nombre no debe superar los 100 caracteres",true);
            return;
        }

        String cedula = txtCedula.getText();
        String nombre  = txtNombre.getText();
        String correo  = txtCorreo.getText();
        String telefono   = txtCelular.getText();
        String direccion = txtDireccion.getText();
        LocalDate fechaNacimiento;


        String fecha_nacimientoS = txtFechaNacimiento.getText(); // "18/08/2027"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            fechaNacimiento = LocalDate.parse(fecha_nacimientoS, formatter);

        }catch(DateTimeParseException e){
            mostrarMensaje("La fecha debe tener el formato dd/mm/yyyy",true);
            return;
        }

        NuevoClienteNaturalDTO nuevoCliente = new NuevoClienteNaturalDTO(cedula,nombre,correo,telefono,fechaNacimiento,direccion);
        

        try{
            clienteNaturalService.insertarClienteNatural(nuevoCliente);
            mostrarMensaje("Cliente registrado exitosamente", false);
            limpiarCampos();
        }catch(ClienteYaExisteException | FechaNacimientoInvalidaException  | CedulaNoValidaException | CelularNoValidoException e){
            mostrarMensaje(e.getMessage(), true);
        }



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

    private void limpiarCampos() {
        txtCedula.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtFechaNacimiento.setText("");
        txtNombre.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteNaturalService = new ClienteNaturalServiceImpl();
    }
}
