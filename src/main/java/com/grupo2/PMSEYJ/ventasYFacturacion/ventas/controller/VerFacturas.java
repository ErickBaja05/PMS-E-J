package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.Factura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerFacturas implements Initializable {

    @FXML
    private TableColumn<Factura, Void> colBotonVer;

    @FXML
    private TableColumn<Factura, String> colEstadoFactura;

    @FXML
    private TableColumn<Factura, String> colFechaEmision;

    @FXML
    private TableColumn<Factura, String> colIdentificacionCliente;

    @FXML
    private TableColumn<Factura, String> colNumeroFactura;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private Label lblIcono;

    @FXML
    private Label lblIconoCalendario1;

    @FXML
    private Label lblIconoCalendario2;

    @FXML
    private Label lblIconoPersona;

    @FXML
    private Label lblTitulo;

    @FXML
    private Button btnIconoBuscar;

    @FXML
    private TableView<Factura> tableFacturas;

    @FXML
    private TextField txtIdentificacionCliente;

    private ObservableList<Factura> listaFacturas;


    @FXML
    public void buscarFacturas(ActionEvent event) {
        listaFacturas =  FXCollections.observableArrayList(new Factura("17/02/2024","12345678910","EMITIDA","1752844322"),
                new Factura("18/02/2024","12345678911","EMITIDA","1752844322"),
                new Factura("19/02/2024","12345678912","EMITIDA","1752844322"),
                new Factura("21/03/2024","12345678913","EMITIDA","1752844322"),
                new Factura("2/03/2024","12345678914","EMITIDA","1752844322")
        );

        colFechaEmision.setCellValueFactory(data -> data.getValue().fechaEmisionProperty());
        colNumeroFactura.setCellValueFactory(data -> data.getValue().numeroFacturaProperty());
        colEstadoFactura.setCellValueFactory(data -> data.getValue().estadoProperty());
        colIdentificacionCliente.setCellValueFactory(data -> data.getValue().identificacionClienteProperty());
        agregarBotonAccion();

        tableFacturas.setItems(listaFacturas);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-clipboard");
        icon.getStyleClass().add("lblIcono");
        FontIcon icon2 = new FontIcon("fa-calendar");
        icon2.getStyleClass().add("lblIconoCalendario1");
        FontIcon icon3 = new FontIcon("fa-calendar");
        icon3.getStyleClass().add("lblIconoCalendario2");
        FontIcon icon4 = new FontIcon("fa-id-card");
        icon4.getStyleClass().add("lblIconoPersona");
        FontIcon icon5 = new FontIcon("fa-search");
        icon5.getStyleClass().add("searchIcon");
        btnIconoBuscar.setGraphic(icon5);
        lblIcono.setGraphic(icon);
        lblIconoCalendario1.setGraphic(icon2);
        lblIconoCalendario2.setGraphic(icon3);
        lblIconoPersona.setGraphic(icon4);

        lblIcono.setText(null);
        lblIconoCalendario1.setText(null);
        lblIconoCalendario2.setText(null);
        lblIconoPersona.setText(null);




    }


    private void agregarBotonAccion() {
        Callback<TableColumn<Factura, Void>, TableCell<Factura, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn;

            {
                btn = new Button();
                btn.getStyleClass().add("botonVerFactura");
                FontIcon icon = new FontIcon("fa-eye");
                icon.getStyleClass().add("insideButtonIcon");

                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color: #bab830; -fx-text-fill: white;");


// Cambiar cursor al pasar el mouse
                btn.setOnMouseEntered(e -> {
                    btn.setCursor(Cursor.HAND);
                    btn.setStyle("-fx-background-color: #838117; -fx-text-fill: white;");
                });

                btn.setOnMouseExited(e -> {
                    btn.setCursor(Cursor.DEFAULT);
                    btn.setStyle("-fx-background-color: #bab830; -fx-text-fill: white;");
                });

                btn.setContentDisplay(ContentDisplay.LEFT); // icono a la izquierda del texto

                btn.setOnAction(event -> {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventasYFacturacion/ventas/fxml/item-facturaSRI.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    NavigationUtil.openNewWindow(event,root,"Informacion de la factura");


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
        colBotonVer.setCellFactory(cellFactory);
    }
}
