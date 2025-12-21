package com.grupo2.PMSEYJ.proveedores.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Optional;

public class consultarPedidoActualPendienteController {

    @FXML private Button btnAnadir, btnEliminar, btnEnviar, btnImport, btnModificar;
    @FXML private TableColumn<?, ?> colCodigo, colNombre, colPrecio;
    @FXML private ImageView imgProducto;
    @FXML private TableView<Object> tablaPedido;
    @FXML private TextField txtCantidad, txtCantidad1, txtCodigo, txtCodigo1;

    // --- SISTEMA DE VENTANAS EMERGENTES (ALERTS) ---

    /**
     * Lanza una ventana de confirmación con botones Aceptar/Cancelar.
     */
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

    /**
     * Lanza una ventana informativa para éxito o error.
     */
    private void mostrarNotificacion(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // --- ACCIONES DEL PEDIDO ---

    @FXML
    void anadirProducto(ActionEvent event) {
        if (txtCodigo.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            mostrarNotificacion("Campos Vacíos", "Debe ingresar el código y la cantidad.", Alert.AlertType.WARNING);
            return;
        }

        if (confirmarAccion("Confirmar Adición", "¿Desea añadir este producto al pedido?")) {
            mostrarNotificacion("Éxito", "Producto añadido exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarNotificacion("Cancelado", "La acción ha sido rechazada.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void modificarProducto(ActionEvent event) {
        // Validación simple: debe haber seleccionado algo
        if (txtCodigo.getText().isEmpty()) {
            mostrarNotificacion("Selección Requerida", "Debe seleccionar primero un producto de la tabla.", Alert.AlertType.WARNING);
            return;
        }

        String cantidadStr = txtCantidad.getText();
        // Validación de tipo de dato (Solo números enteros positivos)
        if (!cantidadStr.matches("\\d+") || Integer.parseInt(cantidadStr) <= 0) {
            mostrarNotificacion("Error de Cantidad", "Cantidad no válida, el pedido no se ha modificado.", Alert.AlertType.ERROR);
            return;
        }

        if (confirmarAccion("Confirmar Modificación", "¿Está seguro de modificar la cantidad?")) {
            mostrarNotificacion("Éxito", "Pedido modificado exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarNotificacion("Cancelado", "La modificación ha sido rechazada.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        if (txtCodigo.getText().isEmpty()) {
            mostrarNotificacion("Selección Requerida", "Seleccione un producto para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (confirmarAccion("Confirmar Eliminación", "¿Desea eliminar el producto del pedido?")) {
            mostrarNotificacion("Éxito", "Pedido modificado exitosamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarNotificacion("Cancelado", "La eliminación ha sido rechazada.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void enviarPedido(ActionEvent event) {
        String proveedor = txtCantidad1.getText(); // txtCantidad1 representa el Proveedor

        if (tablaPedido.getItems().isEmpty()) {
            mostrarNotificacion("Sin Pedidos", "No existen pedidos pendientes.", Alert.AlertType.WARNING);
            return;
        }

        if (proveedor.isEmpty()) {
            mostrarNotificacion("Proveedor Requerido", "Debe ingresar el nombre del proveedor.", Alert.AlertType.WARNING);
            return;
        }

        if (confirmarAccion("Enviar Pedido", "¿Desea enviar el pedido al proveedor " + proveedor + "?")) {
            // Lógica simple de éxito según Caso de Uso
            mostrarNotificacion("Éxito", "Pedido enviado exitosamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            tablaPedido.getItems().clear();
        } else {
            mostrarNotificacion("Cancelado", "El envío ha sido rechazado.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void seleccionarProducto(MouseEvent event) {
        Object seleccionado = tablaPedido.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Simulación de carga de datos al seleccionar la fila
            mostrarNotificacion("Información", "Datos cargados para edición.", Alert.AlertType.INFORMATION);
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtCodigo1.clear();
        txtCantidad.clear();
        txtCantidad1.clear();
    }
}