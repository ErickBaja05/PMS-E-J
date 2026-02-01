package com.grupo2.PMSEYJ.proveedores.controller;



import com.grupo2.PMSEYJ.proveedores.dto.ProveedorDTO;
import com.grupo2.PMSEYJ.proveedores.dto.ResumenPedidoDTO;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresService;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresServiceImpl;
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

public class enviarPedidoController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmarEnvio;

    @FXML
    private ComboBox<String> cmbProveedores;

    @FXML
    private TableColumn<ResumenPedidoDTO, Integer> colCantidad;

    @FXML
    private TableColumn<ResumenPedidoDTO, String> colNombre;

    @FXML
    private TableView<ResumenPedidoDTO> tvReporte;

    private Integer id_pedido;
    private List<ResumenPedidoDTO>  resumenPedido;
    private final ObservableList<ResumenPedidoDTO> productosPedido = FXCollections.observableArrayList();
    private ProveedoresService proveedoresService;

    @FXML
    void handleCancelar(ActionEvent event) {
        mostrarAlerta("No se ha enviado el pedido al proveedor", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleConfirmarEnvio(ActionEvent event) {
        if(cmbProveedores.getSelectionModel().getSelectedItem() == null){
            mostrarAlerta("No existe un proveedor con el nombre proporcionado",  Alert.AlertType.ERROR);
            return;
        }

        ProveedorDTO proveedorDTO = proveedoresService.consultarProveedorNombre(cmbProveedores.getSelectionModel().getSelectedItem());

        proveedoresService.definirProveedorAPedido(id_pedido,proveedorDTO.getId_prove());
        proveedoresService.enviarPedido(id_pedido,"E");
        mostrarAlerta("Pedido enviado al proveedor satisfactoriamente", Alert.AlertType.INFORMATION);
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


    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setResumenPedido(List<ResumenPedidoDTO> resumenPedido) {
        this.resumenPedido = resumenPedido;
        productosPedido.clear();
        productosPedido.addAll(resumenPedido);
        tvReporte.setItems(productosPedido);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_pro"));
        proveedoresService = new ProveedoresServiceImpl();
        List<ProveedorDTO> proveedores = proveedoresService.consultarTodosLosProveedores();
        List<String> nombresProveedores = new ArrayList<>();
        for (ProveedorDTO proveedor : proveedores) {
            nombresProveedores.add(proveedor.getNombre_pro());
        }
        cmbProveedores.setItems(FXCollections.observableArrayList(nombresProveedores));

    }
}



