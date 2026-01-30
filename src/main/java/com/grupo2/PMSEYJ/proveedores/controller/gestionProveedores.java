package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.exception.CelularNoValidoException;
import com.grupo2.PMSEYJ.core.exception.CorreoNoValidoException;
import com.grupo2.PMSEYJ.proveedores.dao.ProveedorDAO;
import com.grupo2.PMSEYJ.proveedores.dto.ProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresService;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.sound.midi.Soundbank;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class gestionProveedores implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEmilinar;

    @FXML
    private Button btnModificar;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCorreoProveedor;

    @FXML
    private TextField txtNombreProveedor;

    @FXML
    private TextField txtNombreResultado;

    @FXML
    private TextField txtTelefonoCelularProveedor;

    private ProveedoresService proveedoresService;
    private ProveedorDTO proveedorConsultado = null;


    @FXML
    void consultarProveedor(ActionEvent event) {
        if (txtNombreProveedor.getText().isEmpty()) {
            mostrarMensaje("No existe un proveedor con el nombre proporcionado!", true);
            return;
        }
        try{
            ProveedorDTO proveedor = proveedoresService.consultarProveedorPorNombre(txtNombreProveedor.getText().toUpperCase());
            txtNombreResultado.setText(proveedor.getNombre_pro());
            txtCorreoProveedor.setText(proveedor.getCorreo_pro());
            txtTelefonoCelularProveedor.setText(proveedor.getTelefono_pro());
            mostrarMensaje("Datos consultados exitosamente", false);
            proveedorConsultado = proveedor;
            btnEmilinar.setDisable(false);
            btnModificar.setDisable(false);

        }catch(IllegalArgumentException ex){
            mostrarMensaje(ex.getMessage(), true);
        }


    }

    @FXML
    void eliminarProveedor(ActionEvent event) {
        try{
            proveedoresService.eliminarProveedorPorNombre(proveedorConsultado.getNombre_pro().toUpperCase());
            mostrarMensaje("Proveedor eliminado exitosamente", false);
        }catch(IllegalArgumentException ex){
            mostrarMensaje(ex.getMessage(), true);
        }


    }

    @FXML
    void modificarDatosProveedor(ActionEvent event) {


        if(txtCorreoProveedor.getText().length() > 100) {
            mostrarMensaje("El nuevo electrónico no puede exceder los 100 caracteres",true);
            return;
        }

        if(!txtTelefonoCelularProveedor.getText().matches("\\d{10}")) {
            mostrarMensaje("El nuevo número de teléfono celular es incorrecto, ingrese solo números y que sean 10",true);
            return;
        }

        try{
            proveedoresService.actualizarCorreoPorNombre(txtCorreoProveedor.getText(), proveedorConsultado.getNombre_pro());
            proveedoresService.actualizarTelefonoPorNombre(txtTelefonoCelularProveedor.getText(), proveedorConsultado.getNombre_pro());
            mostrarMensaje("Datos actualizados exitosamente", false);
        }catch(IllegalArgumentException | CorreoNoValidoException | CelularNoValidoException e){
            mostrarMensaje(e.getMessage(),true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            proveedoresService = new ProveedoresServiceImpl();
            btnEmilinar.setDisable(true);
            btnModificar.setDisable(true);
    }

    private void mostrarMensaje(String texto, boolean esError) {
        if(esError) {
            lblMensaje.getStyleClass().setAll("mensajeError");
        }else{
            lblMensaje.getStyleClass().setAll("mensajeConfirmacion");
        }
        lblMensaje.setText(texto);
    }

    private void validarFormato(String valor, String regex, String mensajeError) {
        if (!valor.trim().matches(regex)) {
            throw new IllegalArgumentException(mensajeError);
        }
    }
}

