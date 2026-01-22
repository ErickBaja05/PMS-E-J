package com.grupo2.PMSEYJ.inventarioYProductos.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarProducto implements Initializable {

    @FXML
    private Button btnCrearProducto;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboFormaVenta;

    @FXML
    private ComboBox<String> comboTipoVenta;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCodigoAuxiliar;

    @FXML
    private TextField txtCodigoBarras;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtIndiceTerapeutico;

    @FXML
    private TextField txtLaboratorio;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPVP;

    private final String [] categorias = {"MEDICAMENTO", "HIGIENE", "OTROS"};
    private final String [] formasVenta = {"POR CAJA", "POR UNIDAD"};
    private final String [] tipoVenta = {"OTC - VENTA LIBRE", "PRESENTACIÓN RECETA", "RETENCIÓN RECETA"};


    @FXML
    void crearProducto(ActionEvent event) {
        try{
            validarVacio(txtCodigoAuxiliar,"Codigo Auxiliar");
            validarVacio(txtCodigoBarras,"Codigo Barras");
            validarVacio(txtNombreProducto,"Nombre");
            validarVacio(txtDescripcion,"Descripcion");
            validarVacio(txtPVP,"PVP");
            validarVacio(txtIndiceTerapeutico,"Indice Terapeutico");
            validarVacio(txtLaboratorio,"Laboratorio");
            validarSeleccion(comboCategoria,"Categoria");
            validarSeleccion(comboFormaVenta,"Forma de Venta");
            validarSeleccion(comboTipoVenta,"Tipo Venta");


            validarFormato(txtCodigoAuxiliar.getText(),"[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ]{5,100}$", "El código auxiliar del producto no cumple con el formato deseado");
            validarFormato(txtCodigoBarras.getText(),"[0-9]+","El código de barras solo deben ser números");


            mostrarMensaje("Producto registrado exitosamente",false);

        }catch(Exception e){
            mostrarMensaje(e.getMessage(), true);
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboCategoria.getItems().addAll(categorias);
        comboFormaVenta.getItems().addAll(formasVenta);
        comboTipoVenta.getItems().addAll(tipoVenta);




    }

    // --- MÉTODOS DE VALIDACIÓN PERSONALIZADOS ---

    private void validarVacio(TextField campo, String nombreCampo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío.");
        }
    }

    private void validarSeleccion(ComboBox campo, String nombreCampo) {
        if(campo.getValue()== null ){
            throw new IllegalArgumentException("Debe seleccionar una opcion en " + nombreCampo);
        }
    }


    private void validarFormato(String valor, String regex, String mensajeError) {
        if (!valor.trim().matches(regex)) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    // --- APOYO ---

    private void mostrarMensaje(String texto, boolean esError) {
        lblMensaje.setText(texto);
        if(!esError){
            lblMensaje.getStyleClass().remove("mensajeError");
            lblMensaje.getStyleClass().add("mensajeConfirmacion");
        }else{
            lblMensaje.getStyleClass().remove("mensajeConfirmacion");
            lblMensaje.getStyleClass().add("mensajeError");
        }
    }
}

