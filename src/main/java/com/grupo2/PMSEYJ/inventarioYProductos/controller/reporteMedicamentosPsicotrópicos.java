package com.grupo2.PMSEYJ.inventarioYProductos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class reporteMedicamentosPsicotrópicos implements Initializable {

    @FXML
    private Button btnIconoBuscar;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colValor;

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
    private Label lblIconoPdf;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<?> tableFacturas;

    @FXML
    void buscarProductos(ActionEvent event) {

    }

    @FXML
    void generarReporte(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-clipboard");
        icon.getStyleClass().add("lblIcono");
        FontIcon icon2 = new FontIcon("fa-calendar");
        icon2.getStyleClass().add("lblIconoCalendario1");
        FontIcon icon3 = new FontIcon("fa-calendar");
        icon3.getStyleClass().add("lblIconoCalendario2");
        FontIcon icon4 = new FontIcon("fa-search");
        icon4.getStyleClass().add("searchIcon");
        FontIcon icon5 = new FontIcon("fa-file-pdf-o");
        icon5.getStyleClass().add("pdfIcon");

        lblIcono.setText(null);
        lblIcono.setGraphic(icon);

        lblIconoCalendario1.setText(null);
        lblIconoCalendario1.setGraphic(icon2);

        lblIconoCalendario2.setText(null);
        lblIconoCalendario2.setGraphic(icon3);

        btnIconoBuscar.setGraphic(icon4);

        lblIconoPdf.setText(null);
        lblIconoPdf.setGraphic(icon5);

    }
}
