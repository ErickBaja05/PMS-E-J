package com.grupo2.PMSEYJ.administracion.dashboard;

import com.grupo2.PMSEYJ.core.util.NavigationUtil;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;


public class ventanaPrincipalController implements Initializable {

    @FXML
    private Button btnAperturaCaja;

    @FXML
    private Button btnArqueoCaja;

    @FXML
    private Button btnAvanceCaja;

    @FXML
    private Button btnCaja;

    @FXML
    private Button btnCierreCaja;

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnConsultarCliente;

    @FXML
    private Button btnConsultarFechaVencimiento;

    @FXML
    private Button btnConsultarPedido;

    @FXML
    private Button btnConsultarStock;

    @FXML
    private Button btnContactoProveedores;

    @FXML
    private Button btnCotejarFactura;

    @FXML
    private Button btnCrearUsuario;

    @FXML
    private Button btnEliminarUsuario;

    @FXML
    private Button btnFacturacion;

    @FXML
    private Button btnFacturar;

    @FXML
    private Button btnFirmaElectronica;

    @FXML
    private Button btnGenerarReporteVentas;

    @FXML
    private Button btnHerramientas;

    @FXML
    private Button btnIngresarFactura;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnMetasIncentivos;

    @FXML
    private Button btnModificarPedido;

    @FXML
    private Button btnModificarProducto;

    @FXML
    private Button btnMovimientosProductos;

    @FXML
    private Button btnParametros;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnProgresoIncentivos;

    @FXML
    private Button btnPromociones;

    @FXML
    private Button btnPromocionesExistentes;

    @FXML
    private Button btnProveedores;

    @FXML
    private Button btnRealizarProforma;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Button btnRegistrarProducto;

    @FXML
    private Button btnRegistrarProducto5;

    @FXML
    private Button btnRegistrarProducto6;

    @FXML
    private Button btnReporteARCSA;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnValeCaja;

    @FXML
    private Button btnVerFacturas;

    @FXML
    private Button btngenerarReporteRentabilidad;

    @FXML
    private VBox cajaBox;

    @FXML
    private VBox cajaSubMenu;

    @FXML
    private VBox clientesBox;

    @FXML
    private VBox clientesSubMenu;

    @FXML
    private VBox facturacionBox;

    @FXML
    private VBox facturacionSubMenu;

    @FXML
    private VBox gestionParametrosBox;

    @FXML
    private VBox gestionUsuariosBox;

    @FXML
    private VBox gestionUsuariosSubMenu;

    @FXML
    private VBox gestiosParametrosSubMenu;

    @FXML
    private VBox inventarioBox;

    @FXML
    private VBox inventarioSubMenu;

    @FXML
    private Label lblLogoMed;

    @FXML
    private Label lblLogoste;

    @FXML
    private VBox menuLateral;

    @FXML
    private VBox pedidosSubMenu;

    @FXML
    private VBox proveedoresBox;

    @FXML
    private VBox proveedoresSubMenu;

    @FXML
    private Pane contenedorPrincipal;

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hasta pronto");
        alert.setHeaderText("Sesión cerrada con éxito");
        alert.setContentText("La sesión se cerró con éxito, debera volvera a iniciar sesión para acceder a cualquier funcionalidad.");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/administracion/fxml/login.fxml"));
        Parent root = loader.load();
        NavigationUtil.changeScene(event,root);

    }

    @FXML
    void mostrarFacturacion(MouseEvent event) {
        ocultarTodos(event);
        facturacionSubMenu.setVisible(true);
        facturacionSubMenu.setManaged(true);
    }

    @FXML
    void ocultarTodos(MouseEvent event) {
        facturacionSubMenu.setVisible(false);
        facturacionSubMenu.setManaged(false);
        pedidosSubMenu.setVisible(false);
        pedidosSubMenu.setManaged(false);
        cajaSubMenu.setVisible(false);
        cajaSubMenu.setManaged(false);
        clientesSubMenu.setVisible(false);
        clientesSubMenu.setManaged(false);
        inventarioSubMenu.setVisible(false);
        inventarioSubMenu.setManaged(false);
        proveedoresSubMenu.setVisible(false);
        proveedoresSubMenu.setManaged(false);
        gestionUsuariosSubMenu.setVisible(false);
        gestionUsuariosSubMenu.setManaged(false);
        gestiosParametrosSubMenu.setVisible(false);
        gestiosParametrosSubMenu.setManaged(false);
    }

    @FXML
    void ocultarPedidos(MouseEvent event) {

    }





    @FXML
    void mostrarPedidos(MouseEvent event) {

        pedidosSubMenu.setVisible(true);
        pedidosSubMenu.setManaged(true);
    }

    @FXML
    void mostrarProveedores(MouseEvent event) {
        ocultarTodos(event);
        proveedoresSubMenu.setVisible(true);
        proveedoresSubMenu.setManaged(true);
    }

    @FXML
    void mostrarCaja(MouseEvent event) {
        ocultarTodos(event);
        cajaSubMenu.setVisible(true);
        cajaSubMenu.setManaged(true);
    }

    @FXML
    void mostrarClientes(MouseEvent event) {
        ocultarTodos(event);
        clientesSubMenu.setVisible(true);
        clientesSubMenu.setManaged(true);
    }



    @FXML
    void mostrarGestionParametros(MouseEvent event) {
        ocultarTodos(event);
        gestiosParametrosSubMenu.setVisible(true);
        gestiosParametrosSubMenu.setManaged(true);
    }

    @FXML
    void mostrarGestionUsuarios(MouseEvent event) {
        ocultarTodos(event);
        gestionUsuariosSubMenu.setVisible(true);
        gestionUsuariosSubMenu.setManaged(true);
    }

    @FXML
    void mostrarInventario(MouseEvent event) {
        ocultarTodos(event);
        inventarioSubMenu.setVisible(true);
        inventarioSubMenu.setManaged(true);
    }

    public void cargarContenido(String rutaFXML) {
        try {
            // Cargar el FXML secundario
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent contenido = loader.load();

            // Limpiar el contenedor y añadir el nuevo contenido
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(contenido);
            contenedorPrincipal.getStyleClass().remove("imagenFondo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void facturarProductos(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/facturarProductos.fxml");
    }

    @FXML
    void realizarProforma(ActionEvent event) {

    }

    @FXML
    void verFacturas(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verFacturas.fxml");

    }

    @FXML
    void verProgresoIncentivos(ActionEvent event) {

    }

    @FXML
    void verPromocionesExistentes(ActionEvent event) {

    }

    @FXML
    void verReporteARCSA(ActionEvent event) {

    }

    @FXML
    void verReporteDeVentas(ActionEvent event) {

    }

    @FXML
    void verReporteRentabilidad(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon("fa-angle-double-left");
        icon.getStyleClass().add("buttonsIcon");
        FontIcon icon2 = new FontIcon("fa-stethoscope");
        icon2.getStyleClass().add("stethoscopeIcon");
        FontIcon icon3 = new FontIcon("fa-file");
        icon3.getStyleClass().add("buttonsIcon");
        FontIcon icon4 = new FontIcon("fa-money");
        icon4.getStyleClass().add("buttonsIcon");
        FontIcon icon5 = new FontIcon("fa-users");
        icon5.getStyleClass().add("buttonsIcon");
        FontIcon icon6 = new FontIcon("fa-medkit");
        icon6.getStyleClass().add("buttonsIcon");
        FontIcon icon7 = new FontIcon("fa-motorcycle");
        icon7.getStyleClass().add("buttonsIcon");
        FontIcon icon8 = new FontIcon("fa-user-circle");
        icon8.getStyleClass().add("buttonsIcon");
        FontIcon icon9 = new FontIcon("fa-wrench");
        icon9.getStyleClass().add("buttonsIcon");
        FontIcon icon10 = new FontIcon("fa-user-md");
        icon10.getStyleClass().add("userMdIcon");

        btnLogOut.setGraphic(icon);
        btnFacturacion.setGraphic(icon3);
        btnCaja.setGraphic(icon4);
        btnCliente.setGraphic(icon5);
        btnInventario.setGraphic(icon6);
        btnProveedores.setGraphic(icon7);
        btnUsuarios.setGraphic(icon8);
        btnParametros.setGraphic(icon9);
        lblLogoste.setGraphic(icon2);
        lblLogoste.setText(null);
        lblLogoMed.setGraphic(icon10);
        lblLogoMed.setText(null);


        facturacionSubMenu.setOnMouseEntered(e -> {
            btnFacturacion.getStyleClass().add("navBtn-activo");
        });

        facturacionSubMenu.setOnMouseExited(e -> {
            btnFacturacion.getStyleClass().remove("navBtn-activo");
        });

        cajaSubMenu.setOnMouseEntered(e -> {
            btnCaja.getStyleClass().add("navBtn-activo");
        });

        cajaSubMenu.setOnMouseExited(e -> {
            btnCaja.getStyleClass().remove("navBtn-activo");
        });
        clientesSubMenu.setOnMouseEntered(e -> {
            btnCliente.getStyleClass().add("navBtn-activo");
        });

        clientesSubMenu.setOnMouseExited(e -> {
            btnCliente.getStyleClass().remove("navBtn-activo");
        });
        inventarioSubMenu.setOnMouseEntered(e -> {
            btnInventario.getStyleClass().add("navBtn-activo");
        });

        inventarioSubMenu.setOnMouseExited(e -> {
            btnInventario.getStyleClass().remove("navBtn-activo");
        });
        proveedoresSubMenu.setOnMouseEntered(e -> {
            btnProveedores.getStyleClass().add("navBtn-activo");
        });

        proveedoresSubMenu.setOnMouseExited(e -> {
            btnProveedores.getStyleClass().remove("navBtn-activo");
        });
        gestiosParametrosSubMenu.setOnMouseEntered(e -> {
            btnParametros.getStyleClass().add("navBtn-activo");
        });

        gestiosParametrosSubMenu.setOnMouseExited(e -> {
            btnParametros.getStyleClass().remove("navBtn-activo");
        });
        gestionUsuariosSubMenu.setOnMouseEntered(e -> {
            btnUsuarios.getStyleClass().add("navBtn-activo");
        });

        gestionUsuariosSubMenu.setOnMouseExited(e -> {
            btnUsuarios.getStyleClass().remove("navBtn-activo");
        });


    }








}

