package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class definirPromocionController {

    @FXML private Button btValidar;
    @FXML private Button btnCancelar;
    @FXML private Button btnRegistrar;
    @FXML private DatePicker dpFechaFin;
    @FXML private DatePicker dpFechaInicio;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCodigoProducto;
    @FXML private TextArea txtCondiciones;

    // Bandera lógica para el flujo del caso de uso
    private boolean productoValidado = false;

    @FXML
    void validarProducto(ActionEvent event) {
        String codigo = txtCodigoProducto.getText().trim();
        lblMensaje.setText(""); // Limpiar mensajes previos

        // ESCENARIO ALTERNATIVO 1: Código no válido (ejemplo: vacío o formato incorrecto)
        if (codigo.isEmpty() || !codigo.matches("^[a-zA-Z0-9-]+$")) {
            mostrarMensaje("Código no valido, ingrese nuevamente", true);
            productoValidado = false;
            return;
        }

        // ESCENARIO ALTERNATIVO 2: El producto no existe
        // Aquí llamarías a tu lógica de negocio/DB. Ejemplo simulado:
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
        // Primero verificamos que el producto haya sido validado previamente
        if (!productoValidado) {
            mostrarMensaje("Debe validar un producto existente primero", true);
            return;
        }

        // ESCENARIO ALTERNATIVO 3: Condiciones no validas
        if (txtCondiciones.getText().trim().isEmpty() || txtCondiciones.getText().length() < 5) {
            mostrarMensaje("Condiciones no validas", true);
            return;
        }

        // ESCENARIO ALTERNATIVO 4: Fecha de inicio no valida
        LocalDate fechaInicio = dpFechaInicio.getValue();
        if (fechaInicio == null || fechaInicio.isBefore(LocalDate.now())) {
            mostrarMensaje("Fecha de inicio no valida", true);
            return;
        }

        // ESCENARIO ALTERNATIVO 5: Fecha de finalización no valida
        LocalDate fechaFin = dpFechaFin.getValue();
        if (fechaFin == null || fechaFin.isBefore(fechaInicio)) {
            mostrarMensaje("Fecha de finalización no valida", true);
            return;
        }

        // ESCENARIO BÁSICO: Registro exitoso
        boolean exitoAlGuardar = registrarEnBaseDeDatos(
                txtCodigoProducto.getText(),
                txtCondiciones.getText(),
                fechaInicio,
                fechaFin
        );

        if (exitoAlGuardar) {
            mostrarMensaje("Promoción registrada", false);
            limpiarCampos();
        }
    }

    @FXML
    void cancelarOperacion(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText("Operación cancelada.");
        lblMensaje.setTextFill(Color.GRAY);
    }

    // --- Métodos de apoyo ---

    private void mostrarMensaje(String texto, boolean esError) {
        lblMensaje.setText(texto);
        lblMensaje.setTextFill(esError ? Color.RED : Color.GREEN);
    }

    private void limpiarCampos() {
        txtCodigoProducto.clear();
        txtCondiciones.clear();
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        productoValidado = false;
    }

    // Simulación de lógica de negocio (Sustituir con servicios reales)
    private boolean verificarExistenciaProductoEnDB(String codigo) {
        // Lógica de búsqueda real aquí
        return !codigo.equals("000"); // Simula que el código "000" no existe
    }

    private boolean registrarEnBaseDeDatos(String cod, String cond, LocalDate inicio, LocalDate fin) {
        // Lógica de persistencia real aquí
        return true;
    }
}