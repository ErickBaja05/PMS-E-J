package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.model.ResultadoItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class resultadoCotejoController {

    @FXML private TextField txtFacturaMostrada;
    @FXML private TextField txtEstadoMostrado;

    @FXML private TableView<ResultadoItem> tvResultados;
    @FXML private TableColumn<ResultadoItem, String> colProducto;
    @FXML private TableColumn<ResultadoItem, Integer> colEsperado;
    @FXML private TableColumn<ResultadoItem, Integer> colRecibido;
    @FXML private TableColumn<ResultadoItem, Integer> colDiferencia;
    @FXML private TableColumn<ResultadoItem, String> colObservacion; // Estado

    @FXML
    public void initialize() {
        // Uso de Lambdas con Properties
        colProducto.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colEsperado.setCellValueFactory(cell -> cell.getValue().cantFacturadaProperty().asObject());
        colRecibido.setCellValueFactory(cell -> cell.getValue().cantRecibidaProperty().asObject());
        colDiferencia.setCellValueFactory(cell -> cell.getValue().diferenciaProperty().asObject());
        colObservacion.setCellValueFactory(cell -> cell.getValue().estadoProperty());
    }

    // Método para recibir los datos desde la ventana anterior
    public void setDatosResultado(String numFactura, String estado, ObservableList<ResultadoItem> listaResultados) {
        this.txtFacturaMostrada.setText(numFactura);
        this.txtEstadoMostrado.setText(estado);
        this.tvResultados.setItems(listaResultados);

        // Estilo dinámico para el estado
        if (estado.contains("DIFERENCIAS")) {
            txtEstadoMostrado.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            txtEstadoMostrado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) txtFacturaMostrada.getScene().getWindow();
        stage.close();
    }

    @FXML
    void imprimirReporte(ActionEvent event) {
        System.out.println("Imprimiendo reporte de cotejo para factura: " + txtFacturaMostrada.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Impresión");
        alert.setHeaderText(null);
        alert.setContentText("Reporte enviado a la impresora.");
        alert.showAndWait();
    }
}