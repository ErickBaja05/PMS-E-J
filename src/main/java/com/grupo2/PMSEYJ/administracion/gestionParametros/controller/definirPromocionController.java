package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class definirPromocionController implements Initializable {

    // =====================
    // CONTROLES FXML
    // =====================
    @FXML private Button btValidar;
    @FXML private Button btnCancelar;
    @FXML private Button btnRegistrar;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;

    @FXML private Label lblMensaje;
    @FXML private TextArea txtCondiciones;

    // Radio buttons
    @FXML private RadioButton rtDUnProducto;
    @FXML private RadioButton rtDTodaMercaderia;

    // Campos de texto
    @FXML private TextField txtCodigoProducto;
    @FXML private TextField txtCodigoProducto1;

    // ToggleGroup (YA EXISTE EN EL FXML)
    @FXML private ToggleGroup grupoPromocion;

    // =====================
    // ESTADO
    // =====================
    private boolean productoValidado = false;

    // =====================
    // INITIALIZE
    // =====================
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Estado inicial: Un Producto seleccionado
        rtDUnProducto.setSelected(true);
        actualizarEstados(true);

        lblMensaje.setText("");
    }

    // =====================
    // CAMBIO DE TIPO PROMOCIÓN
    // =====================
    @FXML
    void darDescuentoUP(ActionEvent event) {
        actualizarEstados(true);
    }

    @FXML
    void darDescuentoTM(ActionEvent event) {
        actualizarEstados(false);
    }

    private void actualizarEstados(boolean esProductoIndividual) {

        // Un producto
        txtCodigoProducto.setDisable(!esProductoIndividual);
        btValidar.setDisable(!esProductoIndividual);

        // Toda la mercadería
        txtCodigoProducto1.setDisable(esProductoIndividual);

        if (esProductoIndividual) {
            txtCodigoProducto1.clear();
            productoValidado = false;
        } else {
            txtCodigoProducto.clear();
            productoValidado = true; // No requiere validación
        }
    }

    // =====================
    // VALIDAR PRODUCTO
    // =====================
    @FXML
    void validarProducto(ActionEvent event) {

        String codigo = txtCodigoProducto.getText().trim();
        lblMensaje.setText("");

        if (codigo.isEmpty() || !codigo.matches("^[a-zA-Z0-9-]+$")) {
            mostrarMensaje("Código no válido, ingrese nuevamente", true);
            productoValidado = false;
            return;
        }

        boolean existe = verificarExistenciaProductoEnDB(codigo);
        if (!existe) {
            mostrarMensaje("Producto no encontrado", true);
            productoValidado = false;
        } else {
            mostrarMensaje("Producto verificado exitosamente.", false);
            productoValidado = true;
        }
    }

    // =====================
    // REGISTRAR PROMOCIÓN
    // =====================
    @FXML
    void registrarPromocion(ActionEvent event) {

        if (rtDUnProducto.isSelected() && !productoValidado) {
            mostrarMensaje("Debe validar un producto existente primero", true);
            return;
        }

        if (rtDTodaMercaderia.isSelected()
                && txtCodigoProducto1.getText().trim().isEmpty()) {
            mostrarMensaje("Debe ingresar el porcentaje de descuento", true);
            return;
        }

        if (txtCondiciones.getText().trim().length() < 5) {
            mostrarMensaje("Condiciones no válidas (mín. 5 caracteres)", true);
            return;
        }

        LocalDate inicio = dpFechaInicio.getValue();
        LocalDate fin = dpFechaFin.getValue();

        if (inicio == null || inicio.isBefore(LocalDate.now())) {
            mostrarMensaje("Fecha de inicio no válida", true);
            return;
        }

        if (fin == null || fin.isBefore(inicio)) {
            mostrarMensaje("Fecha de finalización no válida", true);
            return;
        }

        mostrarMensaje("Promoción registrada exitosamente", false);
        limpiarCampos();
    }

    // =====================
    // CANCELAR
    // =====================
    @FXML
    void cancelarOperacion(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText("Operación cancelada.");
        lblMensaje.setTextFill(Color.GRAY);
    }

    // =====================
    // UTILIDADES
    // =====================
    private void mostrarMensaje(String texto, boolean esError) {
        lblMensaje.setText(texto);
        lblMensaje.setTextFill(esError ? Color.RED : Color.GREEN);
    }

    private void limpiarCampos() {
        txtCodigoProducto.clear();
        txtCodigoProducto1.clear();
        txtCondiciones.clear();
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        rtDUnProducto.setSelected(true);
        actualizarEstados(true);
    }

    private boolean verificarExistenciaProductoEnDB(String codigo) {
        return !codigo.equals("000");
    }
}
