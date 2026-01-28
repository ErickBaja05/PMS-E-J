package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.GestionMetodoPagoDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.dto.IvaDTO;
import com.grupo2.PMSEYJ.administracion.gestionParametros.model.MetodoPago;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosService;
import com.grupo2.PMSEYJ.administracion.gestionParametros.service.ParametrosServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class configuracionPagosImpuestosController {

    // ==========================================
    // COMPONENTES FXML
    // ==========================================
    @FXML private TextField txtValorIVA;
    @FXML private Label lblIvaActual;
    @FXML private Button btnRegistrarIVA;


    @FXML private Button btnHabilitar;
    @FXML private Button btnDeshabilitar;

    @FXML private TableView<GestionMetodoPagoDTO> tablaMetodosPago;
    @FXML private TableColumn<GestionMetodoPagoDTO, String> colNombre;
    @FXML private TableColumn<GestionMetodoPagoDTO, String> colEstado;

    // ==========================================
    // DATOS (Simulación de Base de Datos)
    // ==========================================
    private  ObservableList<GestionMetodoPagoDTO> listaMetodos;

    private ParametrosService parametrosService;

    @FXML
    public void initialize() {

        parametrosService = new ParametrosServiceImpl();

        IvaDTO iva = new IvaDTO(0.0);

        iva.setNuevo_valor(parametrosService.consultarValorIva());

        // Inicializar valor de IVA (Simulado)
        lblIvaActual.setText(iva.getNuevo_valor().toString() + "%");

        // ===== TableView con LAMBDAS + PROPERTIES =====
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_pago"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado_pago"));

        // Cargar datos de prueba
        try{
            List<GestionMetodoPagoDTO> listaMetodosPago = parametrosService.consultarMetodosPago();
            listaMetodos = FXCollections.observableArrayList(listaMetodosPago);
            tablaMetodosPago.setItems(listaMetodos);
        }catch (RuntimeException e){
            mostrarMensaje("Error al consultar metodos pagos", e.getMessage(), Alert.AlertType.ERROR);
        }


    }

    // ==========================================
    // CASO DE USO 3: definirValorDeIVA
    // ==========================================
    @FXML
    void definirIVA(ActionEvent event) {
        String input = txtValorIVA.getText().trim();

        try {
            double valorIVA = Double.parseDouble(input);

            if (valorIVA > 0 && valorIVA <= 100) {
                IvaDTO iva = new IvaDTO(valorIVA);
                parametrosService.actualizarValorIva(iva);

                lblIvaActual.setText(iva.getNuevo_valor().toString() + "%");

                System.out.println("LOG [Módulo Administración]: Registro del valor del IVA (" + valorIVA +
                        ") por UsuarioAdmin a las " + LocalDateTime.now());

                mostrarMensaje("Éxito", "Valor del IVA registrado correctamente", Alert.AlertType.INFORMATION);
                txtValorIVA.clear();

            } else {
                mostrarMensaje("Error", "El valor del IVA debe ser un numero decimal mayor que 0 y menor o igual a 100",
                        Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "El valor del IVA debe ser un numero decimal mayor que 0 y menor o igual a 100",
                    Alert.AlertType.ERROR);
        }
    }

    // ==========================================
    // CASO DE USO 1: habilitarMétodoDePago
    // ==========================================
    @FXML
    void habilitarMetodo(ActionEvent event) {

        GestionMetodoPagoDTO gestionMetodoPagoDTO = tablaMetodosPago.getSelectionModel().getSelectedItem();
        if(gestionMetodoPagoDTO != null){
            if(gestionMetodoPagoDTO.getEstado_pago().equals("DESHABILITADO")){
                mostrarMensaje("Método ya habilitado", "El método de pago: " + gestionMetodoPagoDTO.getNombre_pago() + " Ya se encuentra habilitado",
                        Alert.AlertType.ERROR);
                return;
            }
            parametrosService.habilitarMetodoPago(gestionMetodoPagoDTO.getNombre_pago());
            gestionMetodoPagoDTO.setEstado_pago("HABILITADO");
            tablaMetodosPago.refresh();
            mostrarMensaje("Éxito", "Método de pago habilitado exitosamente", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("Método de pago no seleccionado", "Debe seleccionar un método de pago para habilitar.",
                    Alert.AlertType.ERROR);
        }

    }

    // ==========================================
    // CASO DE USO 2: deshabilitarMétodoDePago
    // ==========================================
    @FXML
    void deshabilitarMetodo(ActionEvent event) {

        GestionMetodoPagoDTO gestionMetodoPagoDTO = tablaMetodosPago.getSelectionModel().getSelectedItem();
        if(gestionMetodoPagoDTO != null){
            if(gestionMetodoPagoDTO.getEstado_pago().equals("DESHABILITADO")){
                mostrarMensaje("Método ya Deshabilitado", "El método de pago: " + gestionMetodoPagoDTO.getNombre_pago() + " Ya se encuentra deshabilitado",
                        Alert.AlertType.ERROR);
                return;
            }
            parametrosService.deshabilitarMetodoPago(gestionMetodoPagoDTO.getNombre_pago());
            gestionMetodoPagoDTO.setEstado_pago("DESHABILITADO");
            tablaMetodosPago.refresh();
            mostrarMensaje("Éxito", "Metodo deshabilitado", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("Método de pago no seleccionado", "Debe seleccionar un método de pago para deshabilitar.",
                    Alert.AlertType.ERROR);
        }
    }

    // ==========================================
    // METODOS AUXILIARES
    // ==========================================


    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
