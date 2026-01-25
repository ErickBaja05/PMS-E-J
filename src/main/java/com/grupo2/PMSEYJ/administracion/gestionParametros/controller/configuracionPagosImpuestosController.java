package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.MetodoPago;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosService;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;

public class configuracionPagosImpuestosController {

    // ==========================================
    // COMPONENTES FXML
    // ==========================================
    @FXML private TextField txtValorIVA;
    @FXML private Label lblIvaActual;
    @FXML private Button btnRegistrarIVA;

    @FXML private TextField txtNombrePago;
    @FXML private Button btnHabilitar;
    @FXML private Button btnDeshabilitar;

    @FXML private TableView<MetodoPago> tablaMetodosPago;
    @FXML private TableColumn<MetodoPago, String> colNombre;
    @FXML private TableColumn<MetodoPago, String> colEstado;

    // ==========================================
    // DATOS (Simulación de Base de Datos)
    // ==========================================
    private final ObservableList<MetodoPago> listaMetodos =
            FXCollections.observableArrayList();

    private ParametrosService parametrosService;

    @FXML
    public void initialize() {

        parametrosService = new ParametrosServiceImpl();

        IvaDTO iva = new IvaDTO(0.0);

        iva.setNuevo_valor(parametrosService.consultarValorIva());

        // Inicializar valor de IVA (Simulado)
        lblIvaActual.setText(iva.getNuevo_valor().toString() + "%");

        // ===== TableView con LAMBDAS + PROPERTIES =====
        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());

        colEstado.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        // Cargar datos de prueba
        listaMetodos.addAll(
                new MetodoPago("Efectivo", "HABILITADO"),
                new MetodoPago("Tarjeta de Crédito", "DESHABILITADO"),
                new MetodoPago("Transferencia", "HABILITADO")
        );

        tablaMetodosPago.setItems(listaMetodos);
    }

    // ==========================================
    // CASO DE USO 3: definirValorDeIVA
    // ==========================================
    @FXML
    void definirIVA(ActionEvent event) {
        String input = txtValorIVA.getText().trim();

        try {
            double valorIVA = Double.parseDouble(input);

            if (valorIVA > 0 && valorIVA <= 100) {
                IvaDTO iva = new IvaDTO(valorIVA);
                parametrosService.actualizarValorIva(iva);

                lblIvaActual.setText(iva.getNuevo_valor().toString() + "%");

                System.out.println("LOG [Módulo Administración]: Registro del valor del IVA (" + valorIVA +
                        ") por UsuarioAdmin a las " + LocalDateTime.now());

                mostrarMensaje("Éxito", "Valor del IVA registrado correctamente", Alert.AlertType.INFORMATION);
                txtValorIVA.clear();

            } else {
                mostrarMensaje("Error", "El valor del IVA debe ser un numero decimal mayor que 0 y menor o igual a 100",
                        Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "El valor del IVA debe ser un numero decimal mayor que 0 y menor o igual a 100",
                    Alert.AlertType.ERROR);
        }
    }

    // ==========================================
    // CASO DE USO 1: habilitarMétodoDePago
    // ==========================================
    @FXML
    void habilitarMetodo(ActionEvent event) {

        String nombrePago = txtNombrePago.getText().trim();

        if (nombrePago.isEmpty()) {
            mostrarMensaje("Aviso", "Ingrese un nombre de método de pago.", Alert.AlertType.WARNING);
            return;
        }

        MetodoPago metodo = buscarMetodo(nombrePago);

        if (metodo != null) {
            metodo.setEstado("HABILITADO");
            tablaMetodosPago.refresh();

            mostrarMensaje("Éxito", "Método de pago habilitado exitosamente",
                    Alert.AlertType.INFORMATION);
            txtNombrePago.clear();
        } else {
            mostrarMensaje("Error", "El método de pago no existe",
                    Alert.AlertType.ERROR);
        }
    }

    // ==========================================
    // CASO DE USO 2: deshabilitarMétodoDePago
    // ==========================================
    @FXML
    void deshabilitarMetodo(ActionEvent event) {

        String nombrePago = txtNombrePago.getText().trim();

        if (nombrePago.isEmpty()) {
            mostrarMensaje("Aviso", "Ingrese un nombre de método de pago.", Alert.AlertType.WARNING);
            return;
        }

        MetodoPago metodo = buscarMetodo(nombrePago);

        if (metodo != null) {
            metodo.setEstado("DESHABILITADO");
            tablaMetodosPago.refresh();

            mostrarMensaje("Éxito", "Método de pago deshabilitado exitosamente",
                    Alert.AlertType.INFORMATION);
            txtNombrePago.clear();
        } else {
            mostrarMensaje("Error", "El método de pago no existe",
                    Alert.AlertType.ERROR);
        }
    }

    // ==========================================
    // METODOS AUXILIARES
    // ==========================================
    private MetodoPago buscarMetodo(String nombre) {
        for (MetodoPago m : listaMetodos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
