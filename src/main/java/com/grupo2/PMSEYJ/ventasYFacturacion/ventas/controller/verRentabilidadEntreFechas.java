package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class verRentabilidadEntreFechas implements Initializable {





    @FXML
    private TableColumn<Venta, String> colFecha;


    @FXML
    private TableColumn<Venta, String> colValor;

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
    private TableView<Venta> tableFacturas;

    @FXML
    private TextField txtIdentificacionCliente;

    @FXML
    private Label lblIconoDolar;

    @FXML
    private TextField txtTotalVentas;

    private ObservableList<Venta> listaVentas;


    @FXML
    public void buscarFacturas(ActionEvent event) {

        listaVentas =  FXCollections.observableArrayList(new Venta("17/02/2024","123.45"),
                new Venta("18/02/2024","145.45"),
                new Venta("19/02/2024","116.45"),
                new Venta("20/02/2024","107.45"),
                new Venta("21/02/2024","169.45")

        );
        if(fechaInicio.getValue() == null || fechaFin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la consulta");
            alert.setHeaderText("Fechas vacias");
            alert.setContentText("Debe ingresar ambas fechas para realizar la consulta");
            alert.showAndWait();

        }else{
            colFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
            colValor.setCellValueFactory(data -> data.getValue().valorProperty());

            tableFacturas.setItems(listaVentas);
            txtTotalVentas.setText("653.36");
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-clipboard");
        icon.getStyleClass().add("lblIcono");
        FontIcon icon2 = new FontIcon("fa-calendar");
        icon2.getStyleClass().add("lblIconoCalendario1");
        FontIcon icon3 = new FontIcon("fa-calendar");
        icon3.getStyleClass().add("lblIconoCalendario2");
        FontIcon icon4 = new FontIcon("fa-dollar");
        icon4.getStyleClass().add("iconoDolarGrande");
        lblIconoDolar.setText(null);
        lblIconoDolar.setGraphic(icon4);
        FontIcon icon5 = new FontIcon("fa-search");
        icon5.getStyleClass().add("searchIcon");
        btnIconoBuscar.setGraphic(icon5);
        lblIcono.setGraphic(icon);
        lblIconoCalendario1.setGraphic(icon2);
        lblIconoCalendario2.setGraphic(icon3);


        lblIcono.setText(null);
        lblIconoCalendario1.setText(null);
        lblIconoCalendario2.setText(null);





    }


}
