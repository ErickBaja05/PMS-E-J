package com.grupo2.PMSEYJ.inventarioYProductos.controller;


import com.grupo2.PMSEYJ.core.exception.CodigoDeBarrasNoValidoException;
import com.grupo2.PMSEYJ.core.exception.ProductoYaExisteException;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoLaboratorioDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.NuevoProductoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductoServiceImpl;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductosService;
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

    private ProductosService productosService;

    @FXML
    void crearProducto(ActionEvent event) {

        if(!validarVacio(txtCodigoAuxiliar,"Codigo Auxiliar")){
            return;
        }

        if(!validarVacio(txtCodigoBarras,"Codigo Barras")){
            return;
        }
        if(!validarVacio(txtNombreProducto,"Nombre")){
            return;
        }
        if(!validarVacio(txtDescripcion,"Descripcion")){
            return;
        }
        if(!validarVacio(txtPVP,"PVP")){
            return;
        }
        if(!validarVacio(txtIndiceTerapeutico,"Indice Terapeutico")){
            return;
        }
        if(!validarVacio(txtLaboratorio,"Laboratorio")){
            return;
        }
        if(!validarSeleccion(comboCategoria,"Categoria")){
            return;
        }
        if(!validarSeleccion(comboFormaVenta,"Forma de Venta")){
            return;
        }

        if(!validarSeleccion(comboTipoVenta,"Tipo Venta")){
            return;
        };

        if(!validarFormato(txtCodigoAuxiliar.getText(),"[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\- ]{2,15}$", "El código auxiliar tiene formato invalido o sobrepasa los 15 caracteres")){
            return;
        };
        if(!validarFormato(txtCodigoBarras.getText(),"[0-9]+","El código de barras solo deben ser números")){
            return;
        }
        if(!validarFormato(txtPVP.getText(),"^\\d+(\\.\\d{1,2})?$","El PVP deben ser solo números. Utilize \".\" para separar decimales (máximo 2)")){
            return;
        }


        String codigo_aux = txtCodigoAuxiliar.getText();
        String codigo_br = txtCodigoBarras.getText();
        String nombre_p = txtNombreProducto.getText();
        String descripcion = txtDescripcion.getText();
        String categoria = null;
        String forma_venta = null;
        String tipo_venta = null;
        switch (comboFormaVenta.getValue()) {
            case "POR CAJA":
                forma_venta = "C";
                break;
            case "POR UNIDAD":
                forma_venta = "U";
                break;

        }

        switch (comboTipoVenta.getValue()) {
            case "OTC - VENTA LIBRE":
                tipo_venta = "O";
                break;
            case "PRESENTACIÓN RECETA":
                tipo_venta = "P";
                break;
            case "RETENCIÓN RECETA":
                tipo_venta = "R";
                break;
        }

        switch (comboCategoria.getValue()) {
            case "MEDICAMENTO":
                categoria = "M";
                break;
            case "HIGIENE":
                categoria = "H";
                break;
            case "OTROS":
                categoria = "O";
                break;
        }


        Double pvp = Double.parseDouble(txtPVP.getText());
        String indice_t = txtIndiceTerapeutico.getText().toUpperCase();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(codigo_aux,codigo_br,nombre_p,descripcion,categoria,forma_venta,tipo_venta,pvp,indice_t);
        NuevoLaboratorioDTO nuevoLaboratorioDTO = new NuevoLaboratorioDTO(txtLaboratorio.getText().toUpperCase());
        try{
            productosService.insertarProducto(nuevoProductoDTO,nuevoLaboratorioDTO);
            mostrarMensaje("Producto registrado exitosamente",false);
        }catch(ProductoYaExisteException | CodigoDeBarrasNoValidoException e){
            mostrarMensaje(e.getMessage(),true);
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productosService = new ProductoServiceImpl();
        comboCategoria.getItems().addAll(categorias);
        comboFormaVenta.getItems().addAll(formasVenta);
        comboTipoVenta.getItems().addAll(tipoVenta);




    }

    // --- MÉTODOS DE VALIDACIÓN PERSONALIZADOS ---

    private boolean validarVacio(TextField campo, String nombreCampo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            mostrarMensaje("El campo " + nombreCampo + " no puede estar vacío.",true);
            return false;

        }
        return true;
    }

    private boolean validarSeleccion(ComboBox campo, String nombreCampo) {
        if(campo.getValue()== null ){
            mostrarMensaje("Debe seleccionar una opcion en " + nombreCampo,true);
            return false;
        }
        return true;
    }


    private boolean validarFormato(String valor, String regex, String mensajeError) {
        if (!valor.trim().matches(regex)) {
            mostrarMensaje(mensajeError,true);
            return false;
        }
        return true;
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

