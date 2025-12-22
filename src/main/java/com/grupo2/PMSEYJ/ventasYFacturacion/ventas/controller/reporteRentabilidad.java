package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.VentaArcsa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class reporteRentabilidad implements Initializable {





    @FXML
    private Button btnDescargarReporte;

    @FXML
    private Button btnIconoBuscar;

    @FXML
    private TableColumn<VentaArcsa, String> colCajasVendidos;

    @FXML
    private TableColumn<VentaArcsa, String> colFracciones;

    @FXML
    private TableColumn<VentaArcsa, String> colPVP;

    @FXML
    private TableColumn<VentaArcsa, String> colPrecioVendido;

    @FXML
    private TableColumn<VentaArcsa, String> colRegistroSanitario;

    @FXML
    private TableColumn<VentaArcsa, String> colValor;

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
    private Label lblTitulo;

    @FXML
    private TableView<VentaArcsa> tableFacturas;

    private ObservableList<VentaArcsa> listaVentas;


    @FXML
    public void buscarFacturas(ActionEvent event) {

        listaVentas =  FXCollections.observableArrayList(new VentaArcsa("12234654654650","APRONAX","0.65","0.90","5","3"),
                new VentaArcsa("45566664343","ALERCET","0.65","0.90","5","3"),
                new VentaArcsa("6665443","IBUPROFENO","0.65","0.90","5","3"),
                new VentaArcsa("67655433","APRONAX","0.65","0.90","5","3"),
                new VentaArcsa("23445566","PEDIALITE","0.65","0.90","5","3")

        );
        if(fechaInicio.getValue() == null || fechaFin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la consulta");
            alert.setHeaderText("Fechas vacias");
            alert.setContentText("Debe ingresar ambas fechas para realizar la consulta");
            alert.showAndWait();
            
        }else{

            colRegistroSanitario.setCellValueFactory(data -> data.getValue().registroSanitarioProperty());
            colValor.setCellValueFactory(data -> data.getValue().productoProperty());
            colPrecioVendido.setCellValueFactory(data -> data.getValue().precioVentaProperty());
            colPVP.setCellValueFactory(data -> data.getValue().PVPProperty());
            colCajasVendidos.setCellValueFactory(data -> data.getValue().numeroCajasVendidasProperty());
            colFracciones.setCellValueFactory(data -> data.getValue().numeroFraccionesVendidasProperty());

            tableFacturas.setItems(listaVentas);

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



    @FXML
    void descargarReporte(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reporte ARCSA");
        alert.setHeaderText("Reporte del ARCSA generado con éxito");
        alert.setContentText("Se descargó el reporte para el sistema REMSAF en formato CSV entre las fechas especificadas");
        alert.showAndWait();

    }


}
