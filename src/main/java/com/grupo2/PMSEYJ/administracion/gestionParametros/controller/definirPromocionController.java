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

    @FXML private Button btValidar, btnCancelar, btnRegistrar;
    @FXML private DatePicker dpFechaFin, dpFechaInicio;
    @FXML private Label lblMensaje;
    @FXML private TextArea txtCondiciones;

    // Elementos de "Un Producto"
    @FXML private RadioButton rtDUnProducto;
    @FXML private TextField txtCodigoProducto;

    // Elementos de "Toda la Mercadería"
    @FXML private RadioButton rtDTodaMercaderia;
    @FXML private TextField txtCodigoProducto1; // Este es el del porcentaje de descuento

    private ToggleGroup grupoPromocion;
    private boolean productoValidado = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Crear el grupo para que sean excluyentes
        grupoPromocion = new ToggleGroup();
        rtDUnProducto.setToggleGroup(grupoPromocion);
        rtDTodaMercaderia.setToggleGroup(grupoPromocion);

        // 2. Estado inicial: Seleccionamos "Un Producto" por defecto
        rtDUnProducto.setSelected(true);
        actualizarEstados(true);
    }

    // --- LÓGICA DE BLOQUEO ---

    @FXML
    void darDescuentoUP(ActionEvent event) {
        actualizarEstados(true);
    }

    @FXML
    void darDescuentoTM(ActionEvent event) {
        actualizarEstados(false);
    }

    private void actualizarEstados(boolean esProductoIndividual) {
        // Si es producto individual, habilitamos sus campos y bloqueamos el otro
        txtCodigoProducto.setDisable(!esProductoIndividual);
        btValidar.setDisable(!esProductoIndividual);

        // Bloqueamos/Habilitamos el campo de porcentaje (Toda la mercadería)
        txtCodigoProducto1.setDisable(esProductoIndividual);

        // Si cambiamos a Mercadería, reseteamos la validación de producto
        if (!esProductoIndividual) {
            productoValidado = true; // No necesita validación de código
            txtCodigoProducto.clear();
        } else {
            productoValidado = false;
            txtCodigoProducto1.clear();
        }
    }

    // --- ACCIONES PRINCIPALES ---

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

    @FXML
    void registrarPromocion(ActionEvent event) {
        // Validación de contexto
        if (rtDUnProducto.isSelected() && !productoValidado) {
            mostrarMensaje("Debe validar un producto existente primero", true);
            return;
        }

        if (rtDTodaMercaderia.isSelected() && txtCodigoProducto1.getText().trim().isEmpty()) {
            mostrarMensaje("Debe ingresar el porcentaje de descuento", true);
            return;
        }

        // Validaciones comunes (Fechas y condiciones)
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

        // Simulación de guardado
        mostrarMensaje("Promoción registrada exitosamente", false);
        limpiarCampos();
    }

    @FXML
    void cancelarOperacion(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText("Operación cancelada.");
        lblMensaje.setTextFill(Color.GRAY);
    }

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