package com.grupo2.PMSEYJ.clientes.controller;

import com.grupo2.PMSEYJ.clientes.dto.GestionClienteJuridicoDTO;
import com.grupo2.PMSEYJ.clientes.service.ClienteJuridicoService;
import com.grupo2.PMSEYJ.clientes.service.ClienteJurididoServiceImpl;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class gestionClienteJController implements Initializable {

    @FXML private Button btnBuscarJ, btnDarAltaJ, btnDarBajaJ, btnModificarJ;
    @FXML private Label lblMensaje;
    @FXML private TextField txtCedulaJ, txtCelularJ, txtCorreoJ, txtDireccionJ, txtEstado, txtNombre;

    private ClienteJuridicoService clienteJuridicoService;
    private GestionClienteJuridicoDTO clienteConsultado;

    // --- 1. CONSULTAR CLIENTE ---
    @FXML
    void consultarClienteJ(ActionEvent event) {
        String RUC = txtCedulaJ.getText().trim();

        // Validación de entrada vacía
        if (RUC.isEmpty()) {
            mostrarMensaje("Por favor, ingrese un RUC para buscar.", true);
            return;
        }

        // Lógica de búsqueda (Simulada para conectar a BD luego)
        try{
            GestionClienteJuridicoDTO cliente = clienteJuridicoService.consultarClienteJuridico(RUC);
            txtNombre.setText(cliente.getRazonSocial());
            txtCorreoJ.setText(cliente.getCorreo());
            txtDireccionJ.setText(cliente.getDireccion());
            txtEstado.setText(cliente.getEstado());
            txtCelularJ.setText(cliente.getTelefono());
            mostrarMensaje("Información del Cliente consultada exitosamente", false);
            clienteConsultado = cliente;
            if(SesionActual.getUsuario().getPerfil_us().equals("AD")){
                btnModificarJ.setDisable(false);
                btnDarBajaJ.setDisable(false);
                btnDarAltaJ.setDisable(false);
            }

        }catch(IllegalArgumentException e){
            limpiarCamposDatos();
            mostrarMensaje(e.getMessage(), true);
        }

    }

    // --- 2, 3 y 4. MODIFICAR DATOS (Celular, Dirección, Correo) ---
    @FXML
    void modificarDatosJ(ActionEvent event) {
        // Verificar que haya un cliente cargado
        if (txtNombre.getText().isEmpty()) {
            mostrarMensaje("Cliente no registrado", true);
            return;
        }

        // Validación CELULAR (Caso de Uso 2)
        String cel = txtCelularJ.getText().trim();
        if (cel.length() != 10) {
            mostrarMensaje("Número de teléfono celular no válido, el número ingresado no tiene 10 dígitos", true);
            return;
        }
        if (!cel.matches("[0-9]+")) {
            mostrarMensaje("Número de teléfono celular no válido, el número ingresado contiene caracteres no permitidos", true);
            return;
        }
        if (!cel.startsWith("09")) {
            mostrarMensaje("Número celular no válido, el número ingresado debe comenzar con 09", true);
            return;
        }


        // Validación DIRECCIÓN (Caso de Uso 3)
        String dir = txtDireccionJ.getText().trim();
        if (dir.isEmpty() || dir.length() > 200) {
            mostrarMensaje("Dirección no válida, la dirección ingresada está vacía o tiene más de 200 caracteres", true);
            return;
        }

        // Validación CORREO (Caso de Uso 4)
        String corr = txtCorreoJ.getText().trim();
        if (corr.isEmpty() || corr.length() > 100) {
            mostrarMensaje("Correo no válido, el correo ingresado está vacío o tiene más de 100 caracteres", true);
            return;
        }

        clienteJuridicoService.actualizarCorreo(txtCedulaJ.getText(),corr);
        clienteJuridicoService.actualizarDireccion(txtCedulaJ.getText(), dir);
        clienteJuridicoService.actualizarTelefono(txtCedulaJ.getText(),cel);
        mostrarMensaje("Datos actualizados correctamente", false);
    }

    // --- 5. DAR DE BAJA ---
    @FXML
    void darBajaClienteJ(ActionEvent event) {


        if (clienteConsultado.getEstado().equals("INACTIVO")) {
            mostrarMensaje("Este cliente ya fue dado de baja", true);
        } else {
            clienteJuridicoService.darDeBaja(clienteConsultado.getRuc());
            txtEstado.setText("INACTIVO");
            clienteConsultado.setEstado("INACTIVO");
            mostrarMensaje("Cliente dado de baja con éxito", false);
        }
    }

    // --- 6. DAR DE ALTA ---
    @FXML
    void darAltaClienteJ(ActionEvent event) {
        if (clienteConsultado.getEstado().equals("ACTIVO")) {
            mostrarMensaje("El cliente se encuentra en estado activo", true);
        } else {
            clienteJuridicoService.darDeAlta(clienteConsultado.getRuc());
            txtEstado.setText("ACTIVO");
            clienteConsultado.setEstado("ACTIVO");
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
        txtCorreoJ.clear();
        txtDireccionJ.clear();
        txtCelularJ.clear();
        txtEstado.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteJuridicoService = new ClienteJurididoServiceImpl();
        btnDarAltaJ.setDisable(true);
        btnDarBajaJ.setDisable(true);
        btnModificarJ.setDisable(true);
    }
}