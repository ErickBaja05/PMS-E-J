package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class definirContactoProveedoresController {

    @FXML private Button btnCancelar;
    @FXML private Button btnRegistrar;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;

    @FXML
    void registrarProveedor(ActionEvent event) {
        try {
            // 1. Validar que nada esté vacío primero
            validarVacio(txtNombre, "Nombre del proveedor");
            validarVacio(txtTelefono, "Número de Teléfono");
            validarVacio(txtCorreo, "Correo Electrónico");

            // 2. Validar formatos específicos
            validarFormato(txtNombre.getText(), "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{5,30}$",
                    "El nombre debe tener entre 5 y 30 letras.");

            validarFormato(txtTelefono.getText(), "\\d{10}",
                    "El teléfono debe tener exactamente 10 números.");

            validarFormato(txtCorreo.getText(), "^[A-Za-z0-9+_.-]+@(.+)$",
                    "El formato del correo no es válido.");

            // 3. Lógica de negocio
            if (verificarSiExiste(txtNombre.getText(), txtCorreo.getText())) {
                throw new IllegalArgumentException("El proveedor ya existe.");
            }

            // Si la operación es exitosa
            mostrarMensaje("Registro exitoso", false);
            limpiarCampos();

        } catch (IllegalArgumentException e) {
            // Captura el mensaje de error de cualquier validación fallida
            mostrarMensaje(e.getMessage(), true);
        }
    }

    // --- MÉTODOS DE VALIDACIÓN PERSONALIZADOS ---

    private void validarVacio(TextField campo, String nombreCampo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío.");
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
        lblMensaje.setTextFill(esError ? Color.RED : Color.DARKGREEN);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
    }

    private boolean verificarSiExiste(String nombre, String correo) {
        // Tu lógica de base de datos aquí
        return false;
    }

    @FXML
    void cancelarOperacion(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText("");
        txtNombre.requestFocus(); // Pone el cursor de nuevo en el nombre
    }
}