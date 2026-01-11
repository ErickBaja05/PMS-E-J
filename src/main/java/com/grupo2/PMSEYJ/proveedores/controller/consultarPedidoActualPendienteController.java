package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.model.ProductoPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class consultarPedidoActualPendienteController {

    @FXML private TextField txtCodigo, txtNombre, txtCantidad;
    @FXML private TableView<ProductoPedido> tvPedidos;
    @FXML private TableColumn<ProductoPedido, Integer> colNum, colCantidad;
    @FXML private TableColumn<ProductoPedido, String> colCodigo, colNombre;

    private ObservableList<ProductoPedido> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // ===== CONFIGURACIÓN DE COLUMNAS (LAMBDAS + PROPERTIES) =====
        colNum.setCellValueFactory(cell -> cell.getValue().numProperty().asObject());

        colCodigo.setCellValueFactory(cell -> cell.getValue().codigoProperty());

        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());

        colCantidad.setCellValueFactory(cell -> cell.getValue().cantidadProperty().asObject());

        // ===== DATOS DE PRUEBA =====
        masterData.add(new ProductoPedido(1, "A100", "Paracetamol 500mg", 10));
        masterData.add(new ProductoPedido(2, "B200", "Ibuprofeno 400mg", 5));

        tvPedidos.setItems(masterData);

        // Placeholder (opcional pero recomendado)
        tvPedidos.setPlaceholder(new Label("No existen productos en el pedido"));
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Metodo auxiliar para buscar el producto en el pedido
    private ProductoPedido buscarEnPedido(String codigo) {
        return masterData.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    @FXML
    void handleBuscar(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        ProductoPedido encontrado = buscarEnPedido(codigo);

        if (encontrado != null) {
            txtNombre.setText(encontrado.getNombre());
            txtCantidad.setText(String.valueOf(encontrado.getCantidad()));
        } else {
            // Escenario Alternativo 1 de ambos Casos de Uso
            mostrarAlerta("Producto no encontrado, el pedido no se ha modificado", Alert.AlertType.ERROR);
            txtNombre.clear();
            txtCantidad.clear();
        }
    }

    @FXML
    void handleModificar(ActionEvent event) {
        // 1. Verificar códigoAuxiliar (Paso 1 y 2)
        String codigo = txtCodigo.getText().trim();
        ProductoPedido producto = buscarEnPedido(codigo);

        if (producto == null) {
            // ESCENARIO ALTERNATIVO 1
            mostrarAlerta("Producto no encontrado, el pedido no se ha modificado", Alert.AlertType.ERROR);
            return;
        }

        // 2. Validar nuevaCantidad (Paso 4)
        try {
            int nuevaCantidad = Integer.parseInt(txtCantidad.getText().trim());

            if (nuevaCantidad > 0) {
                // ESCENARIO BÁSICO (Paso 5 y 6)
                producto.setCantidad(nuevaCantidad);
                tvPedidos.refresh();
                mostrarAlerta("Pedido modificado exitosamente", Alert.AlertType.INFORMATION);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            // ESCENARIO ALTERNATIVO 2
            mostrarAlerta("Cantidad no válida, el pedido no se ha modificado", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleEliminar(ActionEvent event) {
        // 1. Verificar códigoAuxiliar (Paso 1 y 2)
        String codigo = txtCodigo.getText().trim();
        ProductoPedido producto = buscarEnPedido(codigo);

        if (producto != null) {
            // ESCENARIO BÁSICO (Paso 3 y 4)
            masterData.remove(producto);
            mostrarAlerta("Pedido modificado exitosamente", Alert.AlertType.INFORMATION);
            txtCodigo.clear();
            txtNombre.clear();
            txtCantidad.clear();
        } else {
            // ESCENARIO ALTERNATIVO 1
            mostrarAlerta("Producto no encontrado, el pedido no se ha modificado", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleEnviar(ActionEvent event) {
        if (!masterData.isEmpty()) {
            mostrarAlerta("Pedido enviado exitosamente al proveedor", Alert.AlertType.INFORMATION);
            masterData.clear();
        }
    }
}