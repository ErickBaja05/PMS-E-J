package com.grupo2.PMSEYJ.clientes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class gestionClienteNController {

    @FXML private Button btnBuscar, btnDarAlta, btnDarBaja, btnModificar;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCedula, txtCelular, txtCorreo, txtDireccion, txtEstado, txtFechaNacimiento, txtNombre;

    // --- 1. CONSULTAR CLIENTE ---
    @FXML
    void consultarCliente(ActionEvent event) {
        String cedula = txtCedula.getText().trim();

        // Validación de entrada vacía
        if (cedula.isEmpty()) {
            mostrarMensaje("Por favor, ingrese una cédula para buscar.", true);
            return;
        }

        // Lógica de búsqueda (Simulada para conectar a BD luego)
        if (cedula.equals("1234567890")) {
            txtNombre.setText("Juan Pérez");
            txtFechaNacimiento.setText("1990-01-01");
            txtCorreo.setText("juan@mail.com");
            txtDireccion.setText("Calle Falsa 123");
            txtCelular.setText("0987654321");
            txtEstado.setText("ACTIVO");
            mostrarMensaje("Información del Cliente recuperada exitosamente", false);
        } else {
            limpiarCamposDatos();
            mostrarMensaje("No existe un cliente en el Sistema con la cedula de identidad ingresada", true);
        }
    }

    // --- 2, 3 y 4. MODIFICAR DATOS (Celular, Dirección, Correo) ---
    @FXML
    void modificarDatos(ActionEvent event) {
        // Verificar que haya un cliente cargado
        if (txtNombre.getText().isEmpty()) {
            mostrarMensaje("Cliente no registrado", true);
            return;
        }

        // Validación CELULAR (Caso de Uso 2)
        String cel = txtCelular.getText().trim();
        if (cel.length() != 10) {
            mostrarMensaje("Número celular no válido, el número ingresado no tiene 10 dígitos", true);
            return;
        }
        if (!cel.matches("[0-9]+")) {
            mostrarMensaje("Número celular no válido, el número ingresado contiene caracteres no permitidos", true);
            return;
        }
        if (!cel.startsWith("09")) {
            mostrarMensaje("Número celular no válido, el número ingresado con comienza con 09", true);
            return;
        }

        // Validación DIRECCIÓN (Caso de Uso 3)
        String dir = txtDireccion.getText().trim();
        if (dir.isEmpty() || dir.length() > 100) {
            mostrarMensaje("Dirección no válida, la dirección ingresada está vacía o tiene más de 100 caracteres", true);
            return;
        }

        // Validación CORREO (Caso de Uso 4)
        String corr = txtCorreo.getText().trim();
        if (corr.isEmpty() || corr.length() > 50) {
            mostrarMensaje("Correo no válido, el correo ingresado está vacío o tiene más de 50 caracteres", true);
            return;
        }

        // Si todo es correcto:
        mostrarMensaje("Datos actualizados correctamente", false);
    }

    // --- 5. DAR DE BAJA ---
    @FXML
    void darBajaCliente(ActionEvent event) {
        if (txtNombre.getText().isEmpty()) {
            mostrarMensaje("Cliente no registrado", true);
            return;
        }

        if (txtEstado.getText().equals("INACTIVO")) {
            mostrarMensaje("Este cliente ya fue dado de baja", true);
        } else {
            txtEstado.setText("INACTIVO");
            mostrarMensaje("Cliente dado de baja con éxito", false);
        }
    }

    // --- 6. DAR DE ALTA ---
    @FXML
    void darAltaCliente(ActionEvent event) {
        if (txtNombre.getText().isEmpty()) {
            mostrarMensaje("Cliente no registrado", true);
            return;
        }

        if (txtEstado.getText().equals("ACTIVO")) {
            mostrarMensaje("Este cliente ya fue dado de alta", true);
        } else {
            txtEstado.setText("ACTIVO");
            mostrarMensaje("Cliente dado de alta con éxito", false);
        }
    }

    // --- FUNCIONES DE APOYO ---
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

    private void limpiarCamposDatos() {
        txtNombre.clear();
        txtFechaNacimiento.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtCelular.clear();
        txtEstado.clear();
    }
}