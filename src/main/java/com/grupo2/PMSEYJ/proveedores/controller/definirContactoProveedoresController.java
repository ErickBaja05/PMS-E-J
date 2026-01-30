package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.exception.CelularNoValidoException;
import com.grupo2.PMSEYJ.core.exception.CorreoNoValidoException;
import com.grupo2.PMSEYJ.core.exception.NombreNoVálidoException;
import com.grupo2.PMSEYJ.core.exception.ProveedorYaExisteException;
import com.grupo2.PMSEYJ.proveedores.dto.NuevoProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresService;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class definirContactoProveedoresController implements Initializable {

    @FXML private Button btnCancelar;
    @FXML private Button btnRegistrar;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;

    private ProveedoresService proveedoresService;

    @FXML
    void registrarProveedor(ActionEvent event) {
        try {
            // 1. Validar que nada esté vacío primero
            validarVacio(txtNombre);
            validarVacio(txtTelefono);
            validarVacio(txtCorreo);

            // 2. Validar formatos específicos
            if(txtNombre.getText().length() > 100) {
                mostrarMensaje("El nombre del proveedor no puede exceder los 100 caracteres",true);
                return;
            }

            if(!txtTelefono.getText().matches("\\d{10}")) {
                mostrarMensaje("El número de teléfono celular es incorrecto, ingrese solo números y que sean 10",true);
                return;
            }


            if(txtNombre.getText().length() > 100) {
                mostrarMensaje("El correo electrónico no puede exceder los 100 caracteres",true);
                return;
            }

            NuevoProveedorDTO nuevoProveedorDTO = new NuevoProveedorDTO(txtNombre.getText().toUpperCase(), txtTelefono.getText(), txtCorreo.getText());

            proveedoresService.insertarProveedor(nuevoProveedorDTO);
            mostrarMensaje("Proveedor " + nuevoProveedorDTO.getNombre_pro() + " registrado exitosamente",false);


        } catch (IllegalArgumentException | CelularNoValidoException | CorreoNoValidoException | ProveedorYaExisteException | NombreNoVálidoException e) {
            // Captura el mensaje de error de cualquier validación fallida
            mostrarMensaje(e.getMessage(), true);
        }
    }

    // --- MÉTODOS DE VALIDACIÓN PERSONALIZADOS ---

    private void validarVacio(TextField campo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar todos los datos para registrar a un proveedor");
        }
    }


    // --- APOYO ---

    private void mostrarMensaje(String texto, boolean esError) {
        if(esError) {
            lblMensaje.getStyleClass().setAll("mensajeError");
        }else{
            lblMensaje.getStyleClass().setAll("mensajeConfirmacion");
        }
        lblMensaje.setText(texto);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
    }


    @FXML
    void cancelarOperacion(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText("");
        txtNombre.requestFocus(); // Pone el cursor de nuevo en el nombre
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proveedoresService = new ProveedoresServiceImpl();
    }
}