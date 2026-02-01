package com.grupo2.PMSEYJ.proveedores.controller;

import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosService;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosServiceImpl;
import com.grupo2.PMSEYJ.core.exception.*;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ingresarFacturaController implements Initializable {

    ProductosService productosService;
    ProveedoresService proveedoresService;
    ParametrosService parametrosService;

    @FXML
    private Button btnAgregarItem;

    @FXML
    private Button btnGuardarFactura;

    @FXML
    private Button btnValidarFactura;

    @FXML
    private CheckBox checkIVA;

    @FXML
    private TableColumn<ProductoLoteDTO, String > colCodigoBarras;

    @FXML
    private TableColumn<ProductoLoteDTO, String> colLote;

    @FXML
    private TableColumn<ProductoLoteDTO, String> colNombreProducto;

    @FXML
    private TableColumn<ProductoLoteDTO, Double> colPrecioCompra;

    @FXML
    private TableColumn<ProductoLoteDTO, Double> colSubtotal;

    @FXML
    private TableColumn<ProductoLoteDTO, LocalDate> colVencimiento;

    @FXML
    private DatePicker dpVencimiento;

    @FXML
    private TableView<ProductoLoteDTO> tvDetalleFactura;

    @FXML
    private TextField txtCantCajas;

    @FXML
    private TextField txtCodigoBarras;

    @FXML
    private TextField txtLote;

    @FXML
    private TextField txtNumFactura;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtRentabilidad;

    @FXML
    private TextField txtTamañoCaja;

    @FXML
    private ComboBox<String> comboProveedores;

    private Integer id_fc;
    private String num_fc;
    private Integer id_prove;

    private ObservableList<ProductoLoteDTO> listaDetalleFactura = FXCollections.observableArrayList();

    @FXML
    void handleAgregarProducto(ActionEvent event) {
        NuevoLoteDTO nuevoLote = new NuevoLoteDTO();

        if(txtCodigoBarras.getText().isEmpty()){
            mostrarAlerta("El código de barras solo deben ser números y deben ser 13",Alert.AlertType.ERROR);
            return;
        }

        if(comboProveedores.getSelectionModel().getSelectedItem()==null){
            mostrarAlerta("No existe un proveedor con el nombre proporcionado",Alert.AlertType.ERROR);
            return;
        }


        if(txtLote.getText().isEmpty()){
            mostrarAlerta("Lote inválido: El lote solo acepta letras y números, no se permite ningún símbolo",Alert.AlertType.ERROR);
            return;
        }
        if(txtLote.getText().length() > 12){
            mostrarAlerta("Lote inválido: El lote no puede superar los 12 caracteres",Alert.AlertType.ERROR);
            return;
        }

        if(txtPrecioCompra.getText().isEmpty() || !txtPrecioCompra.getText().matches("^\\d+(\\.\\d{1,2})?$")){
            mostrarAlerta("El precio de compra debe ser un número decimal con hasta 2 decimales", Alert.AlertType.ERROR);
            return;
        }
        if(txtRentabilidad.getText().isEmpty() || !txtRentabilidad.getText().matches("[0-9]+" ) || Integer.parseInt(txtRentabilidad.getText()) < 0 ||Integer.parseInt(txtRentabilidad.getText()) > 100 ){
            mostrarAlerta("El porcentaje de rentabilidad debe ser un número entero comprendido entre el 0 y el 100", Alert.AlertType.ERROR);
            return;
        }
        if(!txtCantCajas.getText().matches("[0-9]+")){
            mostrarAlerta("El número de cajas compradas debe ser un valor numérico entero mayor a 0", Alert.AlertType.ERROR);
            return;
        }


        if(dpVencimiento.getValue() == null){
            mostrarAlerta("La fecha de vencimiento no puede estar vacía", Alert.AlertType.ERROR);
            return;
        }

        if(txtTamañoCaja.getText().isEmpty() || !txtTamañoCaja.getText().matches("[0-9]+") || Integer.parseInt(txtTamañoCaja.getText()) < 0){
            mostrarAlerta("El tamaño de caja debe ser un número entero mayor a 0",Alert.AlertType.ERROR);
            return;
        }

        nuevoLote.setCodigo_barras(txtCodigoBarras.getText());
        nuevoLote.setN_cajasCompradas(Integer.valueOf(txtCantCajas.getText()));
        nuevoLote.setNum_lote(txtLote.getText());
        nuevoLote.setFecha_vn(dpVencimiento.getValue());
        nuevoLote.setPrecio_compra(Double.parseDouble(txtPrecioCompra.getText()));
        nuevoLote.setRentabilidad(Integer.valueOf(txtRentabilidad.getText()));
        nuevoLote.setN_cajasCompradas(Integer.valueOf(txtCantCajas.getText()));
        nuevoLote.setTiene_iva(checkIVA.isSelected());
        nuevoLote.setTamano_caja(Integer.valueOf(txtTamañoCaja.getText()));
        System.out.println(nuevoLote.getCodigo_barras());

        try{
            proveedoresService.agregarProducto(id_fc,nuevoLote);
            Producto producto = productosService.consultarProductoPorCodigoBarras(nuevoLote.getCodigo_barras());
            boolean porCaja = producto.getForma_venta().equals("C");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechavn = nuevoLote.getFecha_vn().format(formatter);
            Double iva = parametrosService.consultarValorIva();
            ProductoLoteDTO detalle = new ProductoLoteDTO(nuevoLote.getCodigo_barras(),producto.getNombre_p(), nuevoLote.getNum_lote(),fechavn, nuevoLote.getN_cajasCompradas(), nuevoLote.getPrecio_compra(), nuevoLote.getTamano_caja(), nuevoLote.getRentabilidad(), nuevoLote.getN_cajasCompradas(), porCaja,checkIVA.isSelected(),iva);
            listaDetalleFactura.add(detalle);
            mostrarAlerta("Producto agregado correctamente", Alert.AlertType.INFORMATION);

        }catch(LoteYaExisteException | NumeroDeLoteNoValidoException | FechaFormatoErroneoException | ProductoNoExisteException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void handleGuardarFactura(ActionEvent event) {
        if(tvDetalleFactura.getItems().isEmpty()){
            mostrarAlerta("Debe ingresar al menos 1 producto para registrar cambios en la mercadería", Alert.AlertType.ERROR);
            return;
        }
        try{
            proveedoresService.ingresarFacturaCompra(num_fc,id_prove);
            mostrarAlerta("Mercadería ingresada exitosamente, los stocks de los lotes ingresados se han actualizado", Alert.AlertType.INFORMATION);
            limpiarCamposProducto();
            bloquearControles(true);
        }catch (FacturaYaIngresadaException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void handleValidarFactura(ActionEvent event) {
        if(txtNumFactura.getText().isEmpty() || !txtNumFactura.getText().matches("^[0-9-]+$")){
            mostrarAlerta("El número de factura no es válido, solo se permiten números y el símbolo '- '", Alert.AlertType.ERROR);
            return;
        }

        if(comboProveedores.getSelectionModel().getSelectedItem() == null){
            mostrarAlerta("No existe un proveedor con el nombre proporcionado.", Alert.AlertType.ERROR);
            return;
        }

        NuevaFacturaCompraDTO nuevaFacturaCompra = new NuevaFacturaCompraDTO();
        nuevaFacturaCompra.setFecha_fc(LocalDate.now());

        nuevaFacturaCompra.setNum_fc(txtNumFactura.getText());
        try{
            ProveedorDTO proveedor = proveedoresService.consultarProveedorNombre(comboProveedores.getSelectionModel().getSelectedItem());
            nuevaFacturaCompra.setId_prove(proveedor.getId_prove());
        }catch (IllegalArgumentException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

        try{
            id_fc = proveedoresService.crearFacturaCompra(nuevaFacturaCompra);
            num_fc = nuevaFacturaCompra.getNum_fc();
            id_prove = nuevaFacturaCompra.getId_prove();
            mostrarAlerta("Factura agregada correctamente, comience a ingresar productos", Alert.AlertType.INFORMATION);
            bloquearControles(false);

        }catch(FacturaYaIngresadaException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.INFORMATION);
            id_prove = nuevaFacturaCompra.getId_prove();
            List<CotejoDTO> cotejos = proveedoresService.consultarProductosFacturaPendiente(txtNumFactura.getText(),nuevaFacturaCompra.getId_prove());
            List<ProductoLoteDTO> pendientes = proveedoresService.armarTablaPendiente(cotejos);
            listaDetalleFactura.addAll(pendientes);
            FacturaCompraPendienteDTO facturaPendiente = proveedoresService.consultarFacturaCompra(txtNumFactura.getText(),id_prove);
            id_fc = facturaPendiente.getId_fc();
            num_fc = facturaPendiente.getNum_fc();
            bloquearControles(false);


        }catch(StockYaIngresadoException | NumeroDeFacturaNoValidaException | IllegalArgumentException e){
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    // --- NUEVO: Método auxiliar para bloquear/desbloquear ---
    private void bloquearControles(boolean bloquear) {
        // Si bloquear es TRUE (estado inicial):
        // Factura habilitada, resto deshabilitado.

        // Control de Factura (inverso al resto)
        txtNumFactura.setDisable(!bloquear);
        btnValidarFactura.setDisable(!bloquear);
        comboProveedores.setDisable(!bloquear);
        // Controles de producto y tabla
        txtCodigoBarras.setDisable(bloquear);
        txtLote.setDisable(bloquear);
        dpVencimiento.setDisable(bloquear);
        txtPrecioCompra.setDisable(bloquear);
        txtRentabilidad.setDisable(bloquear);
        txtCantCajas.setDisable(bloquear);
        tvDetalleFactura.setDisable(bloquear);
        txtTamañoCaja.setDisable(bloquear);

        // Botones de acción

        btnAgregarItem.setDisable(bloquear);
        btnGuardarFactura.setDisable(bloquear);


    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Sistema de Proveedores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private void limpiarCamposProducto() {
        txtCodigoBarras.clear();
        txtLote.clear();
        dpVencimiento.setValue(null);
        txtPrecioCompra.clear();
        txtRentabilidad.clear();
        txtCantCajas.clear();
        txtTamañoCaja.clear();
        txtNumFactura.clear();
        checkIVA.selectedProperty().set(false);
        listaDetalleFactura.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bloquearControles(true);
        proveedoresService = new ProveedoresServiceImpl();
        productosService = new ProductoServiceImpl();
        parametrosService = new ParametrosServiceImpl();
        colCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigo_barras"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_pro"));
        colLote.setCellValueFactory(new PropertyValueFactory<>("num_lote"));
        colVencimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_vn"));
        colPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("costo_unitario"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        tvDetalleFactura.setItems(listaDetalleFactura);
        List<ProveedorDTO> proveedores = proveedoresService.consultarTodosLosProveedores();
        List<String> nombres = new ArrayList<>();
        for (ProveedorDTO proveedor : proveedores) {
            nombres.add(proveedor.getNombre_pro());
        }
        comboProveedores.getItems().addAll(nombres);
    }
}







