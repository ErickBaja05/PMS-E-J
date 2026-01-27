package com.grupo2.PMSEYJ.clientes.controller;

import com.grupo2.PMSEYJ.clientes.dto.GestionClienteNaturalDTO;
import com.grupo2.PMSEYJ.clientes.service.ClienteNaturalService;
import com.grupo2.PMSEYJ.clientes.service.ClienteNaturalServiceImpl;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class gestionClienteNController implements Initializable {

    @FXML private Button btnBuscar, btnDarAlta, btnDarBaja, btnModificar;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCedula, txtCelular, txtCorreo, txtDireccion, txtEstado, txtFechaNacimiento, txtNombre;

    private ClienteNaturalService clienteNaturalService;
    private GestionClienteNaturalDTO clienteConsultado;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        try{

            GestionClienteNaturalDTO cliente = clienteNaturalService.consultarCliente(cedula);
            String fechaTexto = cliente.getFecha_nacimiento().format(formatter);
            txtNombre.setText(cliente.getNombre());
            txtCorreo.setText(cliente.getCorreo());
            txtDireccion.setText(cliente.getDireccion());
            txtEstado.setText(cliente.getEstado());
            txtFechaNacimiento.setText(fechaTexto);
            txtCelular.setText(cliente.getTelefono());
            mostrarMensaje("Información del Cliente consultada exitosamente", false);
            clienteConsultado = cliente;
            if(SesionActual.getUsuario().getPerfil_us().equals("AD")){
                btnModificar.setDisable(false);
                btnDarBaja.setDisable(false);
                btnDarAlta.setDisable(false);
            }


        }catch(IllegalArgumentException e){
            limpiarCamposDatos();
            mostrarMensaje(e.getMessage(), true);
        }

    }

    // --- 2, 3 y 4. MODIFICAR DATOS (Celular, Dirección, Correo) ---
    @FXML
    void modificarDatos(ActionEvent event) {

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
        if (dir.isEmpty() || dir.length() > 200) {
            mostrarMensaje("Dirección no válida, la dirección ingresada está vacía o tiene más de 200 caracteres", true);
            return;
        }

        // Validación CORREO (Caso de Uso 4)
        String corr = txtCorreo.getText().trim();
        if (corr.isEmpty() || corr.length() > 100) {
            mostrarMensaje("Correo no válido, el correo ingresado está vacío o tiene más de 100 caracteres", true);
            return;
        }

        // Si todo es correcto:
        clienteNaturalService.actualizarCorreo(txtCedula.getText(),corr);
        clienteNaturalService.actualizarDireccion(txtDireccion.getText(),dir);
        clienteNaturalService.actualizarTelefono(txtCedula.getText(),cel);

        mostrarMensaje("Datos actualizados correctamente", false);
    }

    // --- 5. DAR DE BAJA ---
    @FXML
    void darBajaCliente(ActionEvent event) {

        if (clienteConsultado.getEstado().equals("INACTIVO")) {
            mostrarMensaje("Este cliente ya fue dado de baja", true);
        } else {
            clienteNaturalService.darDeBaja(clienteConsultado.getCedula());
            txtEstado.setText("INACTIVO");
            clienteConsultado.setEstado("INACTIVO");
            mostrarMensaje("Cliente dado de baja con éxito", false);
        }
    }

    // --- 6. DAR DE ALTA ---
    @FXML
    void darAltaCliente(ActionEvent event) {
        if (clienteConsultado.getEstado().equals("ACTIVO")) {
            mostrarMensaje("Este cliente se encuentra activo", true);
        } else {
            clienteNaturalService.darDeAlta(clienteConsultado.getCedula());
            clienteConsultado.setEstado("ACTIVO");
            txtEstado.setText("ACTIVO");
            mostrarMensaje("Cliente dado de alta con éxito", false);
        }
    }

    // --- FUNCIONES DE APOYO ---
    private void mostrarMensaje(String texto, boolean esError) {

        if(esError) {
            lblMensaje.getStyleClass().setAll("mensajeError");

        }else{
            lblMensaje.getStyleClass().setAll("mensajeConfirmacion");

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteNaturalService = new ClienteNaturalServiceImpl();
        btnDarAlta.setDisable(true);
        btnDarBaja.setDisable(true);
        btnModificar.setDisable(true);
    }
}