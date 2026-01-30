package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.exception.CelularNoValidoException;
import com.grupo2.PMSEYJ.core.exception.CorreoNoValidoException;
import com.grupo2.PMSEYJ.proveedores.dto.GestionProveedorDTO;
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

public class gestionProveedores implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnDarDEAlta;

    @FXML
    private Button btnDarDeBaja;

    @FXML
    private Button btnModificar;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCorreoProveedor;

    @FXML
    private TextField txtEstadoProveedor;

    @FXML
    private TextField txtNombreProveedor;

    @FXML
    private TextField txtNombreResultado;

    @FXML
    private TextField txtTelefonoCelularProveedor;

    private ProveedoresService proveedoresService;
    private GestionProveedorDTO proveedorConsultado = null;


    @FXML
    void consultarProveedor(ActionEvent event) {
        if (txtNombreProveedor.getText().isEmpty()) {
            mostrarMensaje("No existe un proveedor con el nombre proporcionado!", true);
            return;
        }
        try{
            GestionProveedorDTO proveedor = proveedoresService.consultarProveedorPorNombre(txtNombreProveedor.getText().toUpperCase());
            txtNombreResultado.setText(proveedor.getNombre_pro());
            txtCorreoProveedor.setText(proveedor.getCorreo_pro());
            txtTelefonoCelularProveedor.setText(proveedor.getTelefono_pro());
            // PARSING ESTADOS
            String estado;
            if(proveedor.getEstado_pv().equals("I")){
                estado = "INACTIVO";
            }else{
                estado = "ACTIVO";
            }
            txtEstadoProveedor.setText(estado);
            mostrarMensaje("Datos consultados exitosamente", false);
            proveedorConsultado = proveedor;
            btnDarDEAlta.setDisable(false);
            btnDarDeBaja.setDisable(false);
            btnModificar.setDisable(false);

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

    @FXML
    void darDeAltaProveedor(ActionEvent event) {
        if(proveedorConsultado.getEstado_pv().equals("A")){
            mostrarMensaje("El proveedor se encuentra activo!",true);
            return;
        }

        txtEstadoProveedor.setText("ACTIVO");
        proveedoresService.darDeAltaProveedor(proveedorConsultado.getNombre_pro());
        proveedorConsultado.setEstado_pv("A");
        mostrarMensaje("Proveedor dado de alta exitosamente",false);

    }

    @FXML
    void darDeBajaProveedor(ActionEvent event) {
        if(proveedorConsultado.getEstado_pv().equals("I")){
            mostrarMensaje("El proveedor se encuentra inactivo!",true);
            return;
        }

        txtEstadoProveedor.setText("INACTIVO");
        proveedoresService.darDeBajaProveedor(proveedorConsultado.getNombre_pro());
        proveedorConsultado.setEstado_pv("I");
        mostrarMensaje("Proveedor dado de baja exitosamente",false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            proveedoresService = new ProveedoresServiceImpl();
            btnDarDEAlta.setDisable(true);
            btnDarDeBaja.setDisable(true);
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

}

