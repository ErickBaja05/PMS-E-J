package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class facturacionController implements Initializable {

    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbFormaPago;
    @FXML private TextField txtIdentificacion, txtCodigoAuxiliar, txtCantidad, txtMontoPago;
    @FXML private Label lblTotalCompra, lblVuelto, lblMensajeSistema;
    @FXML private TableView<Object> tablaDetalle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Llenado de ComboBox con nombres separados
        cmbTipoCliente.getItems().addAll("PERSONA NATURAL", "PERSONA JURÍDICA", "CONSUMIDOR FINAL");
        cmbFormaPago.getItems().addAll("EFECTIVO", "TARJETA", "TRANSFERENCIA");

        // UX: Deshabilitar campo de identificación si es Consumidor Final
        cmbTipoCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("CONSUMIDOR FINAL".equals(newVal)) {
                txtIdentificacion.setDisable(true);
                txtIdentificacion.clear();
            } else {
                txtIdentificacion.setDisable(false);
            }
        });
    }

    // --- MÉTODOS DE APOYO PARA MENSAJES ---

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean confirmarAccion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType btnAceptar = new ButtonType("Aceptar");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnAceptar, btnCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == btnAceptar;
    }

    // --- CASO DE USO: iniciarCompra ---

    @FXML
    void handleIniciarCompra(ActionEvent event) {
        // 1. Verificar estado de Caja (Escenario Básico Paso 1)
        boolean cajaAbierta = true;
        if (!cajaAbierta) {
            // Escenario Alternativo 1
            lblMensajeSistema.setText("Estado: Caja cerrada");
            return;
        }

        String tipo = cmbTipoCliente.getValue();
        String id = txtIdentificacion.getText().trim();

        if (tipo == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de cliente.", Alert.AlertType.WARNING);
            return;
        }

        // ESCENARIO ALTERNATIVO 5: CONSUMIDOR FINAL
        if ("CONSUMIDOR FINAL".equals(tipo)) {
            mostrarAlerta("Éxito", "Compra iniciada, agregue los productos a comprar", Alert.AlertType.INFORMATION);
            lblMensajeSistema.setText("Estado: Compra iniciada (Consumidor Final)");
            lblTotalCompra.setText("$ 0.00");
            return;
        }

        // VALIDACIÓN PARA PERSONA NATURAL O JURÍDICA
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar la identificación del cliente.", Alert.AlertType.WARNING);
            return;
        }

        // Simulación de verificación de existencia (Escenarios Alternativos 2 y 4)
        boolean existeCliente = verificarClienteEnSistema(id, tipo);

        if (existeCliente) {
            mostrarAlerta("Éxito", "Compra iniciada, agregue los productos a comprar", Alert.AlertType.INFORMATION);
            lblMensajeSistema.setText("Estado: Compra iniciada (" + tipo + ")");
            lblTotalCompra.setText("$ 0.00");
        } else {
            mostrarAlerta("Error", "Cliente no registrado", Alert.AlertType.ERROR);
        }
    }

    // --- CASO DE USO: agregarProductoACompra ---

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        String codigo = txtCodigoAuxiliar.getText().trim();
        String cantStr = txtCantidad.getText().trim();

        if (codigo.isEmpty()) {
            mostrarAlerta("Error", "Producto no registrado", Alert.AlertType.ERROR);
            return;
        }

        if (!cantStr.matches("\\d+") || Integer.parseInt(cantStr) <= 0) {
            mostrarAlerta("Error", "Cantidad no válida, producto no agregado a la compra", Alert.AlertType.ERROR);
            return;
        }

        // Escenario Alternativo 5 y 6: Venta bajo receta
        boolean requiereReceta = codigo.startsWith("R-");
        if (requiereReceta) {
            mostrarAlerta("Aviso", "Medicamento de venta bajo receta, verifique la presentación de receta médica", Alert.AlertType.WARNING);
            if (!confirmarAccion("Validar Receta", "¿El cliente presentó la receta médica?")) {
                mostrarAlerta("Cancelado", "Producto no agregado a la compra", Alert.AlertType.INFORMATION);
                return;
            }
        }

        // Simulación de stock (Escenario Alternativo 3)
        if (Integer.parseInt(cantStr) > 20) {
            mostrarAlerta("Error", "Stock insuficiente, producto no agregado a la compra", Alert.AlertType.ERROR);
            return;
        }

        mostrarAlerta("Éxito", "Producto agregado a la compra exitosamente", Alert.AlertType.INFORMATION);
    }

    // --- CASO DE USO: cerrarCompra ---

    @FXML
    void handleCerrarCompra(ActionEvent event) {
        if (lblTotalCompra.getText().equals("$ 0.00")) {
            mostrarAlerta("Aviso", "Compra vacía, cerrando...", Alert.AlertType.WARNING);
            return;
        }

        String formaPago = cmbFormaPago.getValue();
        String montoRecibido = txtMontoPago.getText().trim();

        if (formaPago == null) {
            mostrarAlerta("Error", "Forma de pago no válida", Alert.AlertType.ERROR);
            return;
        }

        try {
            double monto = Double.parseDouble(montoRecibido);
            double total = 50.0; // Simulación del total de la tabla

            if (monto <= 0) {
                mostrarAlerta("Error", "Monto no válido, el monto ingresado no es un valor decimal positivo", Alert.AlertType.ERROR);
                return;
            }

            if (monto < total) {
                mostrarAlerta("Pago Insuficiente", "El monto ingresado no cubre el precio de la compra", Alert.AlertType.ERROR);
                return;
            }

            if (confirmarAccion("Finalizar", "¿Desea cerrar la compra y generar factura?")) {
                double vuelto = monto - total;
                lblVuelto.setText("$ " + String.format("%.2f", vuelto));
                mostrarAlerta("Éxito", "Compra cerrada exitosamente.", Alert.AlertType.INFORMATION);
                limpiarTodo();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Monto no válido, el monto ingresado no es un valor decimal positivo", Alert.AlertType.ERROR);
        }
    }

    private boolean verificarClienteEnSistema(String id, String tipo) {
        // Simulación de búsqueda en base de datos
        return id.length() >= 10;
    }

    private void limpiarTodo() {
        txtIdentificacion.clear();
        txtCodigoAuxiliar.clear();
        txtCantidad.clear();
        txtMontoPago.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbFormaPago.getSelectionModel().clearSelection();
        lblTotalCompra.setText("$ 0.00");
        lblVuelto.setText("$ 0.00");
    }
}