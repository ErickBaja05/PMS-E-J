package com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller;

import com.grupo2.PMSEYJ.ventasYFacturacion.ventas.model.Factura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemFacturaSRI implements Initializable {

    @FXML
    private Button btnAnularFactura;

    @FXML
    private Button btnIconoPdf;

    @FXML
    private Button btnReImprimirFactura;

    @FXML
    private Button btnReImprimirFactura1;

    @FXML
    private TableColumn<?, ?> colCantidadesProducto;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colSubtotal;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private ScrollPane contenedorFacturas;

    @FXML
    private Label lblIconoAnular;

    @FXML
    private Label lblIconoEstado;

    @FXML
    private Label lblIconoFecha;

    @FXML
    private Label lblIconoImpresora;

    @FXML
    private Label lblIconoImpresora1;

    @FXML
    private Label lblIconoNumero;

    @FXML
    private Label lblIdentificacion;

    @FXML
    private TableView<?> tablaFacturas;

    @FXML
    private TextField txtEstadoFactura;

    @FXML
    private TextField txtFechaEmisiòn;

    @FXML
    private TextField txtIdentificaciónCliente;

    @FXML
    private TextField txtNúmeroFactura;

    @FXML
    void anularFactura(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("Anulacion de factura");
        alert.setContentText("Desea anular la factura?");
        Optional<ButtonType> result = alert.showAndWait();
        alert = new Alert(Alert.AlertType.INFORMATION);
        if (result.get() == ButtonType.OK) {
            alert.setTitle("Confirmacion");
            alert.setHeaderText("Anulacion de factura Exitosa");
            alert.setContentText("La facturacion fue anulada exitosamente");

        }else{
            alert.setTitle("Cancelacin");
            alert.setHeaderText("Anulacion de factura cancelada");
            alert.setContentText("Se canceló la operación de anulación de la factura");
        }

        alert.showAndWait();



    }

    @FXML
    void reImprimirFactura(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reimpresion de factura");
        alert.setHeaderText("Factura reimpresa con éxito");
        alert.setContentText("La factura se ha reimpreso correctamente");
        alert.showAndWait();



    }

    @FXML
    void reEnviarEmail(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Re envio de email");
        alert.setHeaderText("Email Reenviado con exito");
        alert.setContentText("Se reenvio la factura al email del cliente con èxito");
        alert.showAndWait();



    }

    @FXML
    void verFactura(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reimpresion de factura");
        alert.setHeaderText("Factura reimpresa con éxito");
        alert.setContentText("La factura se ha reimpreso correctamente");
        alert.showAndWait();



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-newspaper-o");
        icon.getStyleClass().add("iconos");
        lblIconoNumero.setText(null);
        lblIconoNumero.setGraphic(icon);
        FontIcon icon2 = new FontIcon("fa-user");
        icon2.getStyleClass().add("iconos");
        lblIdentificacion.setText(null);
        lblIdentificacion.setGraphic(icon2);
        FontIcon icon3 = new FontIcon("fa-calendar");
        icon3.getStyleClass().add("iconos");
        lblIconoFecha.setText(null);
        lblIconoFecha.setGraphic(icon3);
        FontIcon icon4 = new FontIcon("fa-check-circle");
        icon4.getStyleClass().add("iconos");
        lblIconoEstado.setText(null);
        lblIconoEstado.setGraphic(icon4);
        FontIcon icon5 = new FontIcon("fa-calendar-times-o");
        icon5.getStyleClass().add("iconos");
        lblIconoAnular.setText(null);
        lblIconoAnular.setGraphic(icon5);
        FontIcon icon6 = new FontIcon("fa-print");
        icon6.getStyleClass().add("iconos");
        lblIconoImpresora.setText(null);
        lblIconoImpresora.setGraphic(icon6);
        FontIcon icon7 = new FontIcon("fa-mail-reply");
        icon7.getStyleClass().add("iconos");
        lblIconoImpresora1.setText(null);
        lblIconoImpresora1.setGraphic(icon7);

        FontIcon icon8 = new FontIcon("fa-file-pdf-o");
        icon8.getStyleClass().add("iconoPdf");
        btnIconoPdf.setGraphic(icon8);

        txtNúmeroFactura.setText("12345678911");
        txtEstadoFactura.setText("EMITIDA");
        txtFechaEmisiòn.setText("18/02/2024");
        txtIdentificaciónCliente.setText("1752844322");

    }
}
