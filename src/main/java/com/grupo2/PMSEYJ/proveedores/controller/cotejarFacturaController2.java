package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.core.exception.FacturaNoExisteException;
import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.inventarioYProductos.model.Producto;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductoServiceImpl;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductosService;
import com.grupo2.PMSEYJ.proveedores.dto.*;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresService;
import com.grupo2.PMSEYJ.proveedores.service.ProveedoresServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class cotejarFacturaController2 implements Initializable {

    @FXML
    private Button btnAgregarItem;

    @FXML
    private Button btnCancelarEdicion;

    @FXML
    private Button btnCotejarFactura;

    @FXML
    private TableColumn<ResumenPedidoDTO, Integer> colCantidad;

    @FXML
    private TableColumn<ResumenPedidoDTO, Integer> colCantidadOrig;

    @FXML
    private TableColumn<ResumenPedidoDTO, String> colProducto;

    @FXML
    private TableColumn<ResumenPedidoDTO, String> colProductoOrig;

    @FXML
    private TableView<ResumenPedidoDTO> tvDetalleFactura;

    @FXML
    private TableView<ResumenPedidoDTO> tvFacturaOriginal;


    @FXML
    private ComboBox<String> comboProveedores;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumFactura;
    private ProveedoresService proveedoresService;
    private ProductosService productosService;
    private ObservableList<ResumenPedidoDTO> productosDeFactura = FXCollections.observableArrayList();
    private ObservableList<ResumenPedidoDTO> productosQueLlegaron = FXCollections.observableArrayList();
    private FacturaCompraPendienteDTO facturaACotejar;
    private List<ResultadoCotejoDTO> resultadoCotejo = new ArrayList<>();
    private String resultadoDeCotejo = "SIN NOVEDADES DE SOBRANTES NI FALTANTES";

    @FXML
    void buscarFactura(ActionEvent event) {
        if(txtNumFactura.getText().isEmpty() || !txtNumFactura.getText().matches("[0-9-]+")) {
            mostrarAlerta("No existe una factura con el número: " + txtNumFactura.getText() + " del proveedor" + comboProveedores.getSelectionModel().getSelectedItem(),Alert.AlertType.ERROR);
            return;
        }

        if(comboProveedores.getSelectionModel().getSelectedItem() == null){
            mostrarAlerta("No existe una factura con el número: " + txtNumFactura.getText() + " del proveedor" + comboProveedores.getSelectionModel().getSelectedItem(),Alert.AlertType.ERROR);
            return;
        }
        ProveedorDTO proveedor = proveedoresService.consultarProveedorNombre(comboProveedores.getSelectionModel().getSelectedItem());
        try{
            facturaACotejar = proveedoresService.consultarFacturaCompra(txtNumFactura.getText(),proveedor.getId_prove());
            List<CotejoDTO> cotejos = proveedoresService.consultarProductosFacturaPendiente(txtNumFactura.getText(),proveedor.getId_prove());

            for (CotejoDTO cotejo : cotejos) {
                ResumenPedidoDTO resumenPedidoDTO = new ResumenPedidoDTO();
                ResumenPedidoDTO resumenPedidoDTO1 = new ResumenPedidoDTO();

                resumenPedidoDTO.setCantidad(cotejo.getCantidad());
                LotePedidoDTO lotePedidoDTO = proveedoresService.consultarLotePorId(cotejo.getId_lote());
                Producto producto = productosService.consultarProductoPorCodigoBarras(lotePedidoDTO.getCodigo_barras());

                resumenPedidoDTO.setNombre_pro(producto.getNombre_p());
                resumenPedidoDTO1.setNombre_pro(producto.getNombre_p());

                productosDeFactura.add(resumenPedidoDTO);
                productosQueLlegaron.add(resumenPedidoDTO1);
            }

            mostrarAlerta("Factura cargada exitosamente, ingrese la cantidad real recibida", Alert.AlertType.INFORMATION);
        }catch(IllegalArgumentException | FacturaNoExisteException e){
            mostrarAlerta(e.getMessage(),Alert.AlertType.ERROR);
        }



    }

    @FXML
    void cancelarEdicion(ActionEvent event) {
        limpiarCampos();

    }

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        if(txtCantidad.getText().isEmpty() || !txtCantidad.getText().matches("[0-9-]+" ) || Integer.parseInt(txtCantidad.getText()) < 0) {
            mostrarAlerta("La cantidad recibida debe ser un número entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }
        tvDetalleFactura.getSelectionModel().getSelectedItem().setCantidad(Integer.valueOf(txtCantidad.getText()));
        tvDetalleFactura.refresh();

    }

    @FXML
    void handleCotejarFactura(ActionEvent event) throws IOException {
        resultadoCotejo.clear();

        for(ResumenPedidoDTO resumenPedidoDTO : productosQueLlegaron) {
            if(resumenPedidoDTO.getCantidad() == null || resumenPedidoDTO.getCantidad() <= 0) {
                mostrarAlerta("Debe ingresar la cantidad de todos los productos recibidos", Alert.AlertType.ERROR);
                return;

            }
        }


        for (ResumenPedidoDTO resumenPedidoDTO : productosDeFactura) {
            ResultadoCotejoDTO resultado = new ResultadoCotejoDTO(); // NUEVO objeto en cada iteración

            resultado.setNombre_pro(resumenPedidoDTO.getNombre_pro());
            resultado.setCajas_compradas(resumenPedidoDTO.getCantidad());

            ResumenPedidoDTO encontrado = null;
            for (ResumenPedidoDTO resumenPedidoDTO1 : productosQueLlegaron) {
                if (resumenPedidoDTO.getNombre_pro().equals(resumenPedidoDTO1.getNombre_pro())) {
                    encontrado = resumenPedidoDTO1;
                    break; // ya lo encontraste
                }
            }

            if (encontrado != null) {
                resultado.setCajas_recibidas(encontrado.getCantidad());
            }

            resultadoCotejo.add(resultado);
        }


        for (ResultadoCotejoDTO resultadocotejo : resultadoCotejo) {
            int diferencia = resultadocotejo.getCajas_recibidas() - resultadocotejo.getCajas_compradas();
            resultadocotejo.setDiferencia(diferencia);

            if (diferencia == 0) {
                resultadocotejo.setResultado("SIN DIFERENCIAS");
            } else if (diferencia > 0) {
                resultadocotejo.setResultado("SOBRANTE");
                resultadoDeCotejo = "COTEJADO CON DIFERENCIAS";
            } else {
                resultadocotejo.setResultado("FALTANTE");
                resultadoDeCotejo = "COTEJADO CON DIFERENCIAS";
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/proveedores/fxml/generarReporteCotejo.fxml"));
        Parent root = loader.load();

        generarReporteCotejoController controller = loader.getController();
        controller.setResultadoDeCotejo(resultadoDeCotejo);
        controller.setResultadoCotejo(resultadoCotejo);
        controller.setFacturaACotejar(facturaACotejar);
        NavigationUtil.openNewWindow(event,root,"/proveedores/fxml/generarReporteCotejo.fxml");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proveedoresService = new ProveedoresServiceImpl();
        productosService = new ProductoServiceImpl();
        List<ProveedorDTO> proveedores = proveedoresService.consultarTodosLosProveedores();
        List<String> nombre_proveedores = new ArrayList<>();
        for (ProveedorDTO proveedor : proveedores) {
            nombre_proveedores.add(proveedor.getNombre_pro());
        }

        tvDetalleFactura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {

                txtNombre.setText(newValue.getNombre_pro());
            }
        });
        tvFacturaOriginal.setItems(productosDeFactura);
        tvDetalleFactura.setItems(productosQueLlegaron);
        colProductoOrig.setCellValueFactory(new PropertyValueFactory<>("nombre_pro"));
        colCantidadOrig.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_pro"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        comboProveedores.setItems(FXCollections.observableArrayList(nombre_proveedores));

    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos(){
        txtCantidad.setText(null);
        txtNombre.setText(null);
    }
}
