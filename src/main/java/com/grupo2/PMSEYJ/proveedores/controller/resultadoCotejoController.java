package com.grupo2.PMSEYJ.proveedores.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class resultadoCotejoController {

    @FXML private TextField txtFacturaNro; // Asegúrate de agregar fx:id="txtFacturaNro" en el XML
    @FXML private TextField txtEstadoFinal; // Asegúrate de agregar fx:id="txtEstadoFinal" en el XML

    @FXML private TableView<ResultadoItem> tvResultados;
    @FXML private TableColumn<ResultadoItem, String> colProducto;
    @FXML private TableColumn<ResultadoItem, Integer> colEsperado;
    @FXML private TableColumn<ResultadoItem, Integer> colRecibido;
    @FXML private TableColumn<ResultadoItem, Integer> colDiferencia;
    @FXML private TableColumn<ResultadoItem, String> colObservacion; // Esta es la columna "Estado" de la tabla

    private ObservableList<ResultadoItem> listaResultados = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Vincular columnas con los atributos de ResultadoItem
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colEsperado.setCellValueFactory(new PropertyValueFactory<>("esperado"));
        colRecibido.setCellValueFactory(new PropertyValueFactory<>("recibido"));
        colDiferencia.setCellValueFactory(new PropertyValueFactory<>("diferencia"));
        colObservacion.setCellValueFactory(new PropertyValueFactory<>("estadoFila"));

        tvResultados.setItems(listaResultados);
    }

    /**
     * Método para llenar la ventana con la información procesada
     */
    public void setDatosResultado(String nroFactura, String estadoGeneral, ObservableList<ResultadoItem> items) {
        this.txtFacturaNro.setText(nroFactura);
        this.txtEstadoFinal.setText(estadoGeneral);
        this.listaResultados.setAll(items);

        // Estilo visual rápido para el estado general
        if (estadoGeneral.equalsIgnoreCase("COTEJADA CON DETALLE")) {
            txtEstadoFinal.setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold;");
        } else {
            txtEstadoFinal.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        }
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) txtFacturaNro.getScene().getWindow();
        stage.close();
    }

    // --- CLASE DE MODELO INTERNA ---
    public static class ResultadoItem {
        private String producto;
        private int esperado;
        private int recibido;
        private int diferencia;
        private String estadoFila;

        public ResultadoItem(String producto, int esperado, int recibido) {
            this.producto = producto;
            this.esperado = esperado;
            this.recibido = recibido;
            this.diferencia = recibido - esperado; // Cálculo automático de diferencia

            // Lógica para la columna "Estado" dentro de la tabla
            if (this.diferencia == 0) {
                this.estadoFila = "CORRECTO";
            } else if (this.diferencia > 0) {
                this.estadoFila = "SOBRANTE";
            } else {
                this.estadoFila = "FALTANTE";
            }
        }

        // Getters (necesarios para PropertyValueFactory)
        public String getProducto() { return producto; }
        public int getEsperado() { return esperado; }
        public int getRecibido() { return recibido; }
        public int getDiferencia() { return diferencia; }
        public String getEstadoFila() { return estadoFila; }
    }
}