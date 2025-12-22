package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ingresarFacturaController {

    @FXML private TextField txtNumFactura, txtCodBarras, txtCodAuxiliar, txtLote, txtPrecioCompra, txtRentabilidad, txtCantCajas;
    @FXML private DatePicker dpVencimiento;
    @FXML private TableView<Object> tvDetalleFactura; // Cambiar Object por tu clase Modelo si existe

    /**
     * Método para mostrar las alertas de error según los escenarios alternativos.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        // 1. Validar Número de Factura (Paso 9 / Alt 1)
        if (!txtNumFactura.getText().trim().matches("\\d+")) {
            mostrarError("Ingrese un número de factura válido");
            return;
        }

        // 2. Validar Código de Barras (Paso 10 / Alt 2)
        if (txtCodBarras.getText().trim().isEmpty()) {
            mostrarError("El código de barras ingresado no es válido");
            return;
        }

        // 3. Validar Código Auxiliar (Paso 12 / Alt 4)
        if (txtCodAuxiliar.getText().trim().isEmpty()) {
            mostrarError("Código auxiliar ingresado no es válido");
            return;
        }

        // 4. Validar Lote (Paso 14 / Alt 6)
        if (!txtLote.getText().trim().matches("\\d+")) {
            mostrarError("El lote ingresado no es válido");
            return;
        }

        // 5. Validar Fecha de Vencimiento (Paso 15 / Alt 7 y 8)
        LocalDate fecha = dpVencimiento.getValue();
        if (fecha == null) {
            mostrarError("La fecha ingresada no tiene formato válido");
            return;
        }
        if (!fecha.isAfter(LocalDate.now())) {
            mostrarError("La fecha de vencimiento debe ser mayor que la fecha actual");
            return;
        }

        // 6. Validar Precio de Compra (Paso 16 / Alt 9)
        try {
            Double.parseDouble(txtPrecioCompra.getText());
        } catch (NumberFormatException e) {
            mostrarError("El precio de compra no es válido");
            return;
        }

        // 7. Validar Rentabilidad (Paso 17 / Alt 10)
        if (!txtRentabilidad.getText().trim().matches("\\d+")) {
            mostrarError("El porcentaje de rentabilidad debe ser un número entero válido");
            return;
        }

        // 8. Validar Cantidad de Cajas (Paso 18 / Alt 11)
        try {
            int cajas = Integer.parseInt(txtCantCajas.getText());
            if (cajas <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarError("La cantidad de cajas ingresadas no es válida");
            return;
        }

        // SI PASA TODAS LAS VALIDACIONES:
        System.out.println("Producto validado. Listo para agregar a la tabla.");
        // Aquí agregarías el objeto a la TableView
    }

    @FXML
    void handleGuardarFactura(ActionEvent event) {
        // Lógica final de guardado (Paso 23)
        Alert exito = new Alert(Alert.AlertType.INFORMATION);
        exito.setContentText("Factura ingresada con éxito, stock actualizado de cada producto");
        exito.showAndWait();
    }
}