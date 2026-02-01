package com.grupo2.PMSEYJ.proveedores.controller;
import com.grupo2.PMSEYJ.core.exception.ProductoNoExisteException;
import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import com.grupo2.PMSEYJ.inventarioYProductos.dto.ProductoPedidoDTO;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductoServiceImpl;
import com.grupo2.PMSEYJ.inventarioYProductos.service.ProductosService;
import com.grupo2.PMSEYJ.proveedores.dto.DetallePedidoDTO;
import com.grupo2.PMSEYJ.proveedores.dto.NuevoPedidoDTO;
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
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class consultarPedidoActualPendienteController implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<ProductoPedidoDTO, Integer> colCantidad;

    @FXML
    private TableColumn<ProductoPedidoDTO, String> colCodigo;

    @FXML
    private TableColumn<ProductoPedidoDTO, String > colNombre;

    @FXML
    private TableColumn<ProductoPedidoDTO, Integer> colNum;

    @FXML
    private TableView<ProductoPedidoDTO> tvPedidos;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    private ProductosService productosService;
    private ProveedoresService proveedoresService;

    private ObservableList<ProductoPedidoDTO> listaProductos = FXCollections.observableArrayList();

    private Integer numero_producto = 1;
    private NuevoPedidoDTO nuevoPedido = new NuevoPedidoDTO();
    private Integer id_pedido = null;

    @FXML
    void handleBuscar(ActionEvent event) {

        if(txtCodigo.getText().isEmpty()){
            mostrarAlerta("No existe un producto con el código de barras proporcionado, créelo en Productos -> Crear Producto ", Alert.AlertType.ERROR);
            return;
        }
        ProductoPedidoDTO productoPedido;


        try{
            productoPedido = productosService.consultarProductoPorCodigoBarra(txtCodigo.getText());
            txtNombre.setText(productoPedido.getNombre_p());

        }catch (ProductoNoExisteException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }


    @FXML
    void handleEliminar(ActionEvent event) {
        ProductoPedidoDTO productoPedido = new ProductoPedidoDTO();
        if(txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty()){
            mostrarAlerta("No existe un producto en el pedido con el código de barras proporcionado", Alert.AlertType.ERROR);
            return;
        }

        productoPedido.setCodigo_barras(txtCodigo.getText());
        boolean encontrado = false;
        for (ProductoPedidoDTO producto : listaProductos){
            if(producto.getCodigo_barras().equals(productoPedido.getCodigo_barras())){
                mostrarAlerta("Producto eliminado del pedido satisfactoriamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
                listaProductos.remove(producto);
                tvPedidos.refresh();
                proveedoresService.eliminarDetallePedido(id_pedido,producto.getCodigo_barras());
                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            mostrarAlerta("No existe un producto en el pedido con el código de barras proporcionado", Alert.AlertType.ERROR);

        }



    }

    @FXML
    void handleModificar(ActionEvent event) {
        ProductoPedidoDTO productoPedido = new ProductoPedidoDTO();
        if(txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty()){
            mostrarAlerta("No existe un producto en el pedido con el código de barras proporcionado", Alert.AlertType.ERROR);

            return;
        }
        if(txtCantidad.getText().isEmpty() || !txtCantidad.getText().matches("[0-9]+")){
            mostrarAlerta("La nueva cantidad de cajas debe ser un número entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }

        if(Integer.parseInt(txtCantidad.getText()) <= 0 || Integer.parseInt(txtCantidad.getText()) >= 100){
            mostrarAlerta("No puede solicitar más de 100 cajas ni menos de 1", Alert.AlertType.ERROR);
            return;
        }

        productoPedido.setCodigo_barras(txtCodigo.getText());
        productoPedido.setCantidad_cajas(Integer.valueOf(txtCantidad.getText()));
        boolean encontrado = false;
        for (ProductoPedidoDTO producto : listaProductos){
            if(producto.getCodigo_barras().equals(productoPedido.getCodigo_barras())){
                producto.setCantidad_cajas(productoPedido.getCantidad_cajas());
                mostrarAlerta("Cantidad modificada exitosamente", Alert.AlertType.INFORMATION);
                tvPedidos.refresh();
                proveedoresService.actualizarDetallePedido(producto.getCantidad_cajas(),id_pedido,producto.getCodigo_barras());
                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            mostrarAlerta("No existe un producto en el pedido con el código de barras proporcionado", Alert.AlertType.ERROR);

        }

    }

    @FXML
    void handleAgregar(ActionEvent event) {

        ProductoPedidoDTO productoPedido = new ProductoPedidoDTO();

        if(txtNombre.getText().isEmpty()){
            mostrarAlerta("No existe un producto con el código de barras proporcionado, créelo en Productos -> Crear Producto", Alert.AlertType.ERROR);
            return;
        }
        if(txtCantidad.getText().isEmpty() || !txtCantidad.getText().matches("[0-9]+")){
            mostrarAlerta("La cantidad de cajas debe ser un número entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }

        if(Integer.parseInt(txtCantidad.getText()) <= 0 || Integer.parseInt(txtCantidad.getText()) >= 100){
            mostrarAlerta("No puede solicitar más de 100 cajas ni menos de 1", Alert.AlertType.ERROR);
            return;
        }

        productoPedido.setCantidad_cajas(Integer.parseInt(txtCantidad.getText()));
        productoPedido.setNumero_p(numero_producto);
        productoPedido.setNombre_p(txtNombre.getText());
        productoPedido.setCodigo_barras(txtCodigo.getText());

        for (ProductoPedidoDTO producto : listaProductos){
            if(producto.getCodigo_barras().equals(productoPedido.getCodigo_barras())){
                mostrarAlerta("El producto ya se encuentra en el pedido", Alert.AlertType.ERROR);
                return;
            }
            if(producto.getNombre_p().equals(productoPedido.getNombre_p())){
                mostrarAlerta("El producto ya se encuentra en el pedido", Alert.AlertType.ERROR);
                return;
            }
        }
        if(id_pedido == null){
            id_pedido = proveedoresService.insertarPedido();
            nuevoPedido.setId_pedido(id_pedido);
        }

        listaProductos.add(productoPedido);
        numero_producto++;
        DetallePedidoDTO detallePedido = new DetallePedidoDTO();
        detallePedido.setCodigo_barras(productoPedido.getCodigo_barras());
        detallePedido.setCantidad(Integer.valueOf(txtCantidad.getText()));
        detallePedido.setId_pedido(id_pedido);
        proveedoresService.insertarDetallePedido(detallePedido);
        mostrarAlerta("Producto agregado al pedido satisfactoriamente" , Alert.AlertType.INFORMATION);
        limpiarCamposProducto();


    }

    @FXML
    private void enviarPedido(ActionEvent event) throws IOException {

        if(listaProductos.isEmpty()){
            mostrarAlerta("Pedido vacío, debe ingresar al menos 1 producto al pedido", Alert.AlertType.ERROR);
            return;
        }
        List<ResumenPedidoDTO> resumenPedido = new ArrayList<>();
        for(ProductoPedidoDTO producto : listaProductos){
            resumenPedido.add(new ResumenPedidoDTO(producto.getNombre_p(),producto.getCantidad_cajas()));
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/proveedores/fxml/enviarPedido.fxml"));
        Parent root = loader.load();

        enviarPedidoController enviarPedidoController = loader.getController();
        enviarPedidoController.setId_pedido(id_pedido);
        enviarPedidoController.setResumenPedido(resumenPedido);
        NavigationUtil.openNewWindow(event,root,"Enviar Pedido");

    }

    private void limpiarCamposProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCantidad.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productosService = new ProductoServiceImpl();
        proveedoresService = new ProveedoresServiceImpl();
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad_cajas"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_barras"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_p"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("numero_p"));
        tvPedidos.setItems(listaProductos);

        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                txtCodigo.setText(newValue.getCodigo_barras());
                txtNombre.setText(newValue.getNombre_p());
                txtCantidad.setText(String.valueOf(newValue.getCantidad_cajas()));
            }else{
                txtCodigo.setText(oldValue.getCodigo_barras());
                txtNombre.setText(oldValue.getNombre_p());
                txtCantidad.setText(String.valueOf(oldValue.getCantidad_cajas()));
            }
        });

        NuevoPedidoDTO nuevoPedido = proveedoresService.consultarPedidoPendiente();
        if(nuevoPedido != null){
            id_pedido = nuevoPedido.getId_pedido();
            List<ProductoPedidoDTO> productosPendientes = proveedoresService.cargarPedidoPendiente(nuevoPedido.getId_pedido());
            for(ProductoPedidoDTO producto : productosPendientes){
                numero_producto++;
                listaProductos.add(producto);
            }
            mostrarAlerta("Producto pendiente cargado exitosamente", Alert.AlertType.INFORMATION);
        }




    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Sistema de Proveedores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}



