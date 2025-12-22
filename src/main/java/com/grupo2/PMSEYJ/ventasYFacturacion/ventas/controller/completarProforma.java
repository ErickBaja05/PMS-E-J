package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class completarProforma implements Initializable {

    @FXML
    private Button btnFacturar;

    @FXML
    private Label lblIconoDinero;

    @FXML
    private Label lblIconoDolar;
    @FXML
    private Label lblIconoDolar1;

    @FXML
    private Label lblIconoDolar2;

    @FXML
    private Label lblIconoDolar3;

    @FXML
    private Label lblIconoDolar4;

    @FXML
    private Label lblIconoDolar5;

    @FXML
    private Label lblIconoDolar6;


    @FXML
    private Label lblIconoFacturar;

    @FXML
    private Label lblIconoMétodoPago;

    @FXML
    private ComboBox<String> metodosPago;

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtSubDes0;

    @FXML
    private TextField txtSubDes15;

    @FXML
    private TextField txtSubIva0;

    @FXML
    private TextField txtSubIva15;

    @FXML
    private TextField txtTotalPagar;

    @FXML
    void facturar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Venta realizada");
        alert.setHeaderText("Venta registrada exitosamente");
        alert.setContentText("Se envió la factura al correo electrónico del cliente y al sistema del SRI.");
        alert.showAndWait();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-money");
        icon.getStyleClass().add("iconos");
        lblIconoDinero.setText(null);
        lblIconoDinero.setGraphic(icon);
        FontIcon icon2 = new FontIcon("fa-print");
        icon2.getStyleClass().add("iconos");
        lblIconoMétodoPago.setText(null);
        lblIconoMétodoPago.setGraphic(icon2);

        FontIcon icon3 = new FontIcon("fa-send");
        icon3.getStyleClass().add("iconos");
        lblIconoFacturar.setText(null);
        lblIconoFacturar.setGraphic(icon3);

        FontIcon icon4 = new FontIcon("fa-dollar");
        icon4.getStyleClass().add("iconoDolarGrande");
        lblIconoDolar.setText(null);
        lblIconoDolar.setGraphic(icon4);
        FontIcon icon5= new FontIcon("fa-dollar");
        icon5.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar1.setText(null);
        lblIconoDolar1.setGraphic(icon5);
        FontIcon icon6 = new FontIcon("fa-dollar");
        icon6.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar2.setText(null);
        lblIconoDolar2.setGraphic(icon6);
        FontIcon icon7 = new FontIcon("fa-dollar");
        icon7.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar3.setText(null);
        lblIconoDolar3.setGraphic(icon7);
        FontIcon icon8 = new FontIcon("fa-dollar");
        icon8.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar4.setText(null);
        lblIconoDolar4.setGraphic(icon8);
        FontIcon icon9 = new FontIcon("fa-dollar");
        icon9.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar5.setText(null);
        lblIconoDolar5.setGraphic(icon9);
        FontIcon icon10 = new FontIcon("fa-dollar");
        icon10.getStyleClass().add("iconoDolarPequeño");
        lblIconoDolar6.setText(null);
        lblIconoDolar6.setGraphic(icon10);

        txtSubDes0.setText("14.32");
        txtSubDes15.setText("44.32");
        txtSubIva0.setText("54.32");
        txtSubIva15.setText("12.54");
        txtTotalPagar.setText("123");
        txtIva.setText("11.63");
        txtDescuento.setText("4.26");




    }
}
