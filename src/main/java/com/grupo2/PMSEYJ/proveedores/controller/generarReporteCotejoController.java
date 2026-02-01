package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.proveedores.dto.FacturaCompraPendienteDTO;
import com.grupo2.PMSEYJ.proveedores.dto.ResultadoCotejoDTO;
import com.grupo2.PMSEYJ.proveedores.dto.ResumenPedidoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class generarReporteCotejoController implements Initializable {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnImprimir;

    @FXML
    private TableColumn<ResultadoCotejoDTO, Integer> colCantidadReal;

    @FXML
    private TableColumn<ResultadoCotejoDTO, String> colCodigo;

    @FXML
    private TableColumn<ResultadoCotejoDTO, Integer> colDiferencia;

    @FXML
    private TableColumn<ResultadoCotejoDTO, String> colEstadoProducto;

    @FXML
    private TableColumn<ResultadoCotejoDTO, Integer> colUnidadesCompradas;

    @FXML
    private Label lblEstadoActual;

    @FXML
    private Label lblFacturaActual;

    @FXML
    private TableView<ResultadoCotejoDTO> tvReporte;

    private List<ResultadoCotejoDTO> resultadoCotejo = new ArrayList<>();
    private String resultadoDeCotejo;
    private final ObservableList<ResultadoCotejoDTO> cotejo = FXCollections.observableArrayList();
    private FacturaCompraPendienteDTO facturaACotejar;

    public void setResultadoCotejo(List<ResultadoCotejoDTO> resultadoCotejo) {
        this.resultadoCotejo = resultadoCotejo;
        cotejo.clear();
        cotejo.addAll(resultadoCotejo);
        tvReporte.setItems(cotejo);
    }

    public void setResultadoDeCotejo(String resultadoDeCotejo) {
        this.resultadoDeCotejo = resultadoDeCotejo;
        lblEstadoActual.setText(resultadoDeCotejo);
    }

    public void setFacturaACotejar(FacturaCompraPendienteDTO facturaACotejar) {
        this.facturaACotejar = facturaACotejar;
        lblFacturaActual.setText(facturaACotejar.getNum_fc());
    }

    @FXML
    void handleCerrar(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.close();


            Stage parentStage = (Stage) currentStage.getOwner();

            Parent root = FXMLLoader.load(getClass().getResource("/administracion/fxml/ventanaPrincipal.fxml"));
            parentStage.setScene(new Scene(root));
            parentStage.setTitle("Menú Principal");
            parentStage.show();

            parentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleImprimir(ActionEvent event) {
        mostrarAlerta("Reporte de cotejo impreso exitosamente", Alert.AlertType.INFORMATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("nombre_pro"));
        colUnidadesCompradas.setCellValueFactory(new PropertyValueFactory<>("cajas_compradas"));
        colCantidadReal.setCellValueFactory(new PropertyValueFactory<>("cajas_recibidas"));
        colDiferencia.setCellValueFactory(new PropertyValueFactory<>("diferencia"));
        colEstadoProducto.setCellValueFactory(new PropertyValueFactory<>("resultado"));



    }
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
