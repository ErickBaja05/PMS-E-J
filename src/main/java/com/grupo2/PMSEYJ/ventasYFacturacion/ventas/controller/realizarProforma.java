package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.ItemCarrito;
import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class realizarProforma implements Initializable {
    private ObservableList<Producto> listaProductos;
    private ObservableList<ItemCarrito> listaCarrito = FXCollections.observableArrayList();



    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnConsultarVademecum;

    @FXML
    private Button btnFacturar;

    @FXML
    private TableColumn<Producto, Void> colAccion;

    @FXML
    private TableColumn<ItemCarrito, Integer> colCantidad;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colCodigoAux;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<ItemCarrito, Void> colEliminar;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<ItemCarrito, String> colNombreProducto;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<ItemCarrito, Double> colSubtotal;

    @FXML
    private ComboBox<String> opcionesCliente;

    @FXML
    private TableView<ItemCarrito> tablaCarrito;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TextField txtApellidosCliente;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtNombresCliente;

    @FXML
    private TextField txtIdentificacionCliente;

    private String tiposIdentificacion [] = {"CEDULA", "RUC"};

    @FXML
    private void consultarVademecum(ActionEvent event) {
        try {
            // Check if Desktop API is supported
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://mx.mivademecum.com/"));
            } else {
                System.err.println("Desktop browsing not supported on this system.");
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Failed to open website: " + e.getMessage());
        }
    }

    @FXML
    private void consultarCliente(ActionEvent event) {
        try{

        }catch(Exception e){

        }
        if(opcionesCliente.getValue().equals("CEDULA")) {

            if(txtIdentificacionCliente.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error: No se especifico una cédula para la busqueda");
                alert.setContentText("Ingrese un valor de cédula de identidad para consultar los datos de un cliente");
                alert.showAndWait();

            }else if(txtIdentificacionCliente.getText().length() != 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error: La cédula de identidad no tiene una longitud de 10 digitos");
                alert.setContentText("Ingrese un valor de cédula de identidad válido para consultar los datos de un cliente");
                alert.showAndWait();
            }else{
                txtApellidosCliente.setText("NOMBRE DE UN CLIENTE NATURAL");
                txtDireccionCliente.setText("APELLIDO DE UN CLIENTE NATURAL");
                txtNombresCliente.setText("DIRECCION DE UN CLIENTE NATURAL");
                txtEmailCliente.setText("EMAIL DE UN CLIENTE NATURAL");
            }

        }else{
            if(txtIdentificacionCliente.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error: No se especifico un RUC para la busqueda");
                alert.setContentText("Ingrese un valor de RUC para consultar los datos de un cliente");
                alert.showAndWait();

            }else if(txtIdentificacionCliente.getText().length() != 13) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error: El RUC no tiene una longitud de 13 digitos");
                alert.setContentText("Ingrese un valor de RUC para consultar los datos de un cliente");
                alert.showAndWait();
            }else{
                txtApellidosCliente.setText("NOMBRE CLIENTE JURIDICO");
                txtDireccionCliente.setText("APELLIDO DE UN CLIENTE NATURAL");
                txtNombresCliente.setText("DIRECCION DE UN CLIENTE JURIDICO");
                txtEmailCliente.setText("EMAIL DE UN CLIENTE JURIDICO");
            }

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opcionesCliente.getItems().addAll(tiposIdentificacion);
        opcionesCliente.getSelectionModel().select(0);

        txtIdentificacionCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (opcionesCliente.getValue().equals("CEDULA")) {
                // Elimina todo lo que no sea número
                txtIdentificacionCliente.setText(newValue.replaceAll("[^0-9]", ""));
                // Opcional: limitar a 10 dígitos
                if (txtIdentificacionCliente.getText().length() > 10) {
                    txtIdentificacionCliente.setText(txtIdentificacionCliente.getText().substring(0, 10));
                }
            } else if (opcionesCliente.getValue().equals("RUC")) {
                // Elimina todo lo que no sea número
                txtIdentificacionCliente.setText(newValue.replaceAll("[^0-9]", ""));
                // Opcional: validar que termine en 001
                if (!txtIdentificacionCliente.getText().endsWith("001")) {
                    // Aquí decides si corriges automáticamente o solo validas al final
                    // Ejemplo: forzar que termine en 001
                    if (txtIdentificacionCliente.getText().length() >= 13) {
                        txtIdentificacionCliente.setText(
                                txtIdentificacionCliente.getText().substring(0, 10) + "001"
                        );
                    }
                }
            }
        });


        FontIcon icon = new FontIcon("fa-book");
        icon.getStyleClass().add("insideButtonIcon");
        btnConsultarVademecum.setGraphic(icon);

        FontIcon icon2 = new FontIcon("fa-search");
        icon2.getStyleClass().add("searchIcon");
        btnBuscarCliente.setGraphic(icon2);

        FontIcon icon3 = new FontIcon("fa-suitcase");
        icon3.getStyleClass().add("insideButtonIcon");
        btnFacturar.setGraphic(icon3);

        // Simulación de productos (esto luego lo reemplazas con BD)
        listaProductos = FXCollections.observableArrayList(
                new Producto("12345", "APROXEN", "OTC", 5.50, 20),
                new Producto("67890", "ASPIRINA", "Presentación Receta", 2.00, 50),
                new Producto("11121", "APROCAL", "Retención Receta", 3.75, 15)
        );

        // Configurar columnas
        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colDescripcion.setCellValueFactory(data -> data.getValue().tipoProperty());
        colPrecio.setCellValueFactory(data -> data.getValue().precioProperty().asObject());
        colStock.setCellValueFactory(data -> data.getValue().stockProperty().asObject());

        // Columna con botón
        agregarBotonAccion();

        // Filtrado en tiempo real
        FilteredList<Producto> filtrados = new FilteredList<>(listaProductos, p -> true);
        txtNombreProducto.textProperty().addListener((obs, oldValue, newValue) -> {
            filtrados.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filtro = newValue.toLowerCase();
                return producto.getNombre().toLowerCase().contains(filtro)
                        || producto.getCodigo().toLowerCase().contains(filtro);
            });
        });

        tablaProductos.setItems(filtrados);

        // Configurar columnas
        colNombreProducto.setCellValueFactory(data -> data.getValue().productoProperty());
        colCantidad.setCellValueFactory(data -> data.getValue().cantidadProperty().asObject());
        colSubtotal.setCellValueFactory(data -> data.getValue().subtotalProperty().asObject());

        // Cantidad editable con TextField
        colCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colCantidad.setOnEditCommit(event -> {
            ItemCarrito item = event.getRowValue();
            item.cantidadProperty().set(event.getNewValue());
            // recalcular subtotal si quieres
            item.subtotalProperty().set(item.cantidadProperty().get() * 5.0); // ejemplo
        });

        // Botón eliminar
        colEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button btnEliminar ;

            {
                btnEliminar = new Button();
                btnEliminar.getStyleClass().add("botonEliminarCarrito");
                FontIcon icon = new FontIcon("fa-close");
                icon.getStyleClass().add("insideButtonIcon");

                btnEliminar.setGraphic(icon);

                btnEliminar.setStyle("-fx-background-color: #9a1430; -fx-text-fill: white;");


// Cambiar cursor al pasar el mouse
                btnEliminar.setOnMouseEntered(e -> {
                    btnEliminar.setCursor(Cursor.HAND);
                    btnEliminar.setStyle("-fx-background-color: #6a061b; -fx-text-fill: white;");
                });

                btnEliminar.setOnMouseExited(e -> {
                    btnEliminar.setCursor(Cursor.DEFAULT);
                    btnEliminar.setStyle("-fx-background-color: #9a1430; -fx-text-fill: white;");
                });

                btnEliminar.setContentDisplay(ContentDisplay.LEFT); // icono a la izquierda del texto
                btnEliminar.setOnAction(e -> {
                    ItemCarrito item = getTableView().getItems().get(getIndex());
                    listaCarrito.remove(item);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEliminar);
                }
            }
        });

        tablaCarrito.setItems(listaCarrito);
        tablaCarrito.setEditable(true);



    }


    private void agregarBotonAccion() {
        Callback<TableColumn<Producto, Void>, TableCell<Producto, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn;

            {
                btn = new Button();
                btn.getStyleClass().add("botonAñadirCarrito");
                FontIcon icon = new FontIcon("fa-cart-plus");
                icon.getStyleClass().add("insideButtonIcon");

                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color: #2a9d8f; -fx-text-fill: white;");


// Cambiar cursor al pasar el mouse
                btn.setOnMouseEntered(e -> {
                    btn.setCursor(Cursor.HAND);
                    btn.setStyle("-fx-background-color: #21867a; -fx-text-fill: white;");
                });

                btn.setOnMouseExited(e -> {
                    btn.setCursor(Cursor.DEFAULT);
                    btn.setStyle("-fx-background-color: #2a9d8f; -fx-text-fill: white;");
                });

                btn.setContentDisplay(ContentDisplay.LEFT); // icono a la izquierda del texto

                btn.setOnAction(event -> {
                    Producto producto = getTableView().getItems().get(getIndex());
                    // Por defecto cantidad = 1 y subtotal = precio * cantidad
                    ItemCarrito nuevoItem = new ItemCarrito(
                            producto.getNombre(),
                            1,
                            producto.getPrecio() * 1
                    );
                    if(producto.getTipo().equalsIgnoreCase("Presentación Receta")){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Se requiere receta");
                        alert.setHeaderText("Presentación de receta obligatoria");
                        alert.setContentText("Este producto está marcado como PRESENTACIÓN DE RECETA para su venta. Recuerde que no puede distribuirse de forma libre.");
                        DialogPane dialogPane = alert.getDialogPane();

                        dialogPane.setPrefSize(400, 200); // Tamaño personalizado

                        alert.showAndWait();

                    }
                    if(producto.getTipo().equalsIgnoreCase("Retención Receta")){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Se requiere receta");
                        alert.setHeaderText("Retencion de receta obligatoria");
                        alert.setContentText("Este producto está marcado como RETENCION DE RECETA para su venta. Recuerde verificar la veracidad de la factura antes de vender este producto");
                        DialogPane dialogPane = alert.getDialogPane();

                        dialogPane.setPrefSize(400, 200); // Tamaño personalizado
                        alert.showAndWait();
                    }

                    listaCarrito.add(nuevoItem);



                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        colAccion.setCellFactory(cellFactory);
    }

    @FXML
    private void terminarFacturacion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventasYFacturacion/ventas/fxml/completarProforma.fxml"));
        Parent root = loader.load();
        NavigationUtil.openNewWindow(event,root,"Terminar Proforma");

    }

}




