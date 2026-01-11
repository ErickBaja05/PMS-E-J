package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.model.ProductoPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;

public class enviarPedidoController {

    @FXML private TableView<ProductoPedido> tvReporte;
    @FXML private TableColumn<ProductoPedido, String> colNombre;
    @FXML private TableColumn<ProductoPedido, Integer> colCantidad;
    @FXML private ComboBox<String> cmbProveedores;

    // VALORES QUEMADOS: Proveedores registrados
    private final List<String> proveedoresRegistrados = Arrays.asList("DIFFARE", "DIKAPHARMA", "QUIMFALA");

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCantidad.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());

        // Cargar proveedores registrados en el ComboBox
        cmbProveedores.setItems(FXCollections.observableArrayList(proveedoresRegistrados));
    }

    public void cargarReporte(ObservableList<ProductoPedido> datos) {
        tvReporte.setItems(datos);
    }

    @FXML
    void handleConfirmarEnvio(ActionEvent event) {
        String proveedorElegido = cmbProveedores.getValue();

        // 1. Verificar nombreProveedor (Paso 5 y Escenario Alternativo 2)
        if (proveedorElegido == null || !proveedoresRegistrados.contains(proveedorElegido)) {
            mostrarAlerta("Proveedor no encontrado, no se ha enviado el pedido", Alert.AlertType.ERROR);
            return; // El caso de uso termina sin enviar el pedido
        }


        // 2. Escenario Básico: Envío exitoso (Pasos 6 al 10)
        // Aquí el sistema registraría la acción y crearía el pedido vacío
        mostrarAlerta("Pedido enviado exitosamente", Alert.AlertType.INFORMATION);

        // Al limpiar tvReporte, se limpia automáticamente masterData del padre
        tvReporte.getItems().clear(); // Esto cumple con crear un pedido vacío

        // Cerrar ventana tras el éxito
        ((Stage) cmbProveedores.getScene().getWindow()).close();
    }

    @FXML
    void handleCancelar(ActionEvent event) {
        ((Stage) cmbProveedores.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}