package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class verProgresoIncentivosYMetas implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnBuscarPorLaboratorio;

    @FXML
    private TextField codigoProducto;

    @FXML
    private TableColumn<?, ?> colFechaLimiteLaboratorio;

    @FXML
    private TableColumn<?, ?> colIncentivoLaboratorio;

    @FXML
    private TableColumn<?, ?> colIncentivoProducto;

    @FXML
    private TableColumn<?, ?> colLimiteProducto;

    @FXML
    private TableColumn<?, ?> colObjetivoLaboratorio;

    @FXML
    private TableColumn<?, ?> colObjetivoProducto;

    @FXML
    private TableColumn<?, ?> colProgresoLaboratorio;

    @FXML
    private TableColumn<?, ?> colProgresoProducto;

    @FXML
    private TableColumn<?, ?> colUnidadesLaboratorio;

    @FXML
    private TableColumn<?, ?> colUnidadesProducto;

    @FXML
    private TableView<?> tablaIncentivosLaboratorio;

    @FXML
    private TableView<?> tablaIncentivosProducto;

    @FXML
    private TextField txtNombreLaboratorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon2 = new FontIcon("fa-search");
        icon2.getStyleClass().add("searchIcon");
        btnBuscar.setGraphic(icon2);

        FontIcon icon3 = new FontIcon("fa-search");
        icon3.getStyleClass().add("searchIcon");
        btnBuscarPorLaboratorio.setGraphic(icon3);
    }
}