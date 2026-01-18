package com.grupo2.PMSEYJ.administracion.dashboard;

import com.grupo2.PMSEYJ.core.session.SesionActual;
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
    private Button btnConsultarClienteJ;

    @FXML
    private Button btnConsultarClienteN;

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
    private Button btnModificarProducto;

    @FXML
    private Button btnParametros;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnProductosBajaRotacion;

    @FXML
    private Button btnProductosProximosCaducar;

    @FXML
    private Button btnProgresoIncentivos;

    @FXML
    private Button btnPromociones;

    @FXML
    private Button btnPromocionesExistentes;

    @FXML
    private Button btnProveedores;

    @FXML
    private Button btnAdministracion;

    @FXML
    private Button btnRealizarProforma;

    @FXML
    private Button btnRegistrarClienteJ;

    @FXML
    private Button btnRegistrarClienteN;

    @FXML
    private Button btnRegistrarProducto;

    @FXML
    private Button btnRegistrarProducto5;

    @FXML
    private Button btnRegistrarProducto6;

    @FXML
    private Button btnReporteARCSA;

    @FXML
    private Button btnReportePsicotropicos;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnValeCaja;

    @FXML
    private Button btnVerFacturas;

    @FXML
    private Button btngenerarReporteRentabilidad;

    @FXML
    private VBox cajaBoxSubMenu;

    @FXML
    private VBox cajaSubMenu;

    @FXML
    private VBox clientesBox;

    @FXML
    private VBox clientesSubMenu;

    @FXML
    private Pane contenedorPrincipal;

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
    private Label lblUsuario;

    @FXML
    void logOut(ActionEvent event) throws IOException {
        SesionActual.cerrarSesion();
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
    void mostrarClientes(ActionEvent event) {
        boolean abierto = clientesSubMenu.isVisible();
        ocultarTodos();
        if(!abierto){
            clientesSubMenu.setVisible(true);
            clientesSubMenu.setManaged(true);
        }

    }


    @FXML
    void mostrarProveedores(ActionEvent event) {
        boolean abierto = proveedoresSubMenu.isVisible();
        ocultarTodos();
        if(!abierto){
            proveedoresSubMenu.setVisible(true);
            proveedoresSubMenu.setManaged(true);
        }
    }
    @FXML
    void mostrarInventario(ActionEvent event) {
        boolean abierto = inventarioSubMenu.isVisible();
        ocultarTodos();
        if(!abierto){
            inventarioSubMenu.setVisible(true);
            inventarioSubMenu.setManaged(true);
        }

    }
    @FXML
    void mostrarFacturacion(ActionEvent event) {
        boolean abierto = facturacionSubMenu.isVisible();
        ocultarTodos();
        if (!abierto) {
            facturacionSubMenu.setVisible(true);
            facturacionSubMenu.setManaged(true);
        }

    }

    @FXML
    void mostrarCaja(ActionEvent event) {
        boolean abierto = cajaSubMenu.isVisible();
        ocultarCaja();
        if (!abierto) {
            cajaSubMenu.setVisible(true);
            cajaSubMenu.setManaged(true);
        }

    }
    @FXML
    void mostrarAdministracion(ActionEvent event) {
        boolean abierto = gestionUsuariosBox.isVisible() && gestionParametrosBox.isVisible();
        ocultarTodos();
        if (!abierto) {
            gestionUsuariosBox.setVisible(true);
            gestionUsuariosBox.setManaged(true);
            gestionParametrosBox.setVisible(true);
            gestionParametrosBox.setManaged(true);
        }

    }
    @FXML
    void mostrarGestionUsuarios(ActionEvent event) {
        boolean abierto = gestionUsuariosSubMenu.isVisible();
        ocultarGestionUsuarios();
        if (!abierto) {
            gestionUsuariosSubMenu.setVisible(true);
            gestionUsuariosSubMenu.setManaged(true);
        }

    }

    @FXML
    void mostrarGestionParametros(ActionEvent event) {
        boolean abierto = gestiosParametrosSubMenu.isVisible();
        ocultarGestionParametros();
        if (!abierto) {
            gestiosParametrosSubMenu.setVisible(true);
            gestiosParametrosSubMenu.setManaged(true);
        }

    }





    void ocultarTodos() {
        facturacionSubMenu.setVisible(false);
        facturacionSubMenu.setManaged(false);
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
        gestionParametrosBox.setVisible(false);
        gestionParametrosBox.setManaged(false);
        gestionUsuariosBox.setVisible(false);
        gestionUsuariosBox.setManaged(false);
    }

    void ocultarCaja(){
        cajaSubMenu.setVisible(false);
        cajaSubMenu.setManaged(false);
    }

    void ocultarGestionParametros(){
        gestiosParametrosSubMenu.setVisible(false);
        gestiosParametrosSubMenu.setManaged(false);
    }

    void ocultarGestionUsuarios(){
        gestionUsuariosSubMenu.setVisible(false);
        gestionUsuariosSubMenu.setManaged(false);
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
        cargarContenido("/ventasYFacturacion/ventas/fxml/realizarProforma.fxml");

    }

    @FXML
    void verFacturas(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verFacturas.fxml");

    }

    @FXML
    void verProgresoIncentivos(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verProgresoIncentivosYMetas.fxml");

    }

    @FXML
    void verPromocionesExistentes(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verPromocionesVigentes.fxml");
    }

    @FXML
    void verReporteARCSA(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/generarReporteARCSA.fxml");

    }

    @FXML
    void verReporteDeVentas(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verVentasEntreFechas.fxml");

    }

    @FXML
    void verReporteRentabilidad(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/ventas/fxml/verRentabilidadEntreFechas.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(SesionActual.haySesion()){
            lblUsuario.setText(
                    SesionActual.getUsuario().getNombre_us().toUpperCase()
            );
        }
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
        FontIcon icon11 = new FontIcon("fa-briefcase");
        icon11.getStyleClass().add("buttonsIcon");

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
        btnAdministracion.setGraphic(icon11);


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

        gestionUsuariosBox.setOnMouseEntered(e -> {
            btnAdministracion.getStyleClass().add("navBtn-activo");
        });
        gestionUsuariosBox.setOnMouseExited(e -> {
            btnAdministracion.getStyleClass().remove("navBtn-activo");
        });

        gestionParametrosBox.setOnMouseEntered(e -> {
            btnAdministracion.getStyleClass().add("navBtn-activo");
        });
        gestionParametrosBox.setOnMouseExited(e -> {
            btnAdministracion.getStyleClass().remove("navBtn-activo");
        });




    }

    @FXML
    void abrirAjustarStock(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/ajustarStock.fxml");

    }

    @FXML
    void abrirConsultarClienteN(ActionEvent event) {
        cargarContenido("/clientes/fxml/gestionClienteN.fxml");

    }

    @FXML
    void abrirConsultarClienteJ(ActionEvent event) {
        cargarContenido("/clientes/fxml/gestionClienteJ.fxml");

    }

    @FXML
    void abrirProductosBajaRotacion(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/productosBajaRotacion.fxml");

    }
    @FXML
    void abrirProductosProximosCaducar(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/productosProximosCaducar.fxml");

    }
    @FXML
    void abrirReportePsicotropicos(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/reporteMedicamentosPsicotropicos.fxml");

    }

    @FXML
    void abrirConsultarPedido(ActionEvent event) {
        cargarContenido("/proveedores/fxml/consultarPedidoActualPendiente.fxml");

    }

    @FXML
    void abrirConsultarStock(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/consultarStockProducto.fxml");

    }

    @FXML
    void abrirConsultarVencimiento(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/consultarFechaDeVenci.fxml");

    }

    @FXML
    void abrirCotejarFactura(ActionEvent event) {
        cargarContenido("/proveedores/fxml/cotejarFactura2.fxml");

    }

    @FXML
    void abrirCrearUsuario(ActionEvent event) {
        cargarContenido("/administracion/fxml/crearUser.fxml");

    }

    @FXML
    void abrirEliminarUsuario(ActionEvent event) {
        cargarContenido("/administracion/fxml/eliminarUser.fxml");
    }

    @FXML
    void abrirFirmaElectronica(ActionEvent event) {
        cargarContenido("/administracion/fxml/cargarFirmaElectronica.fxml");

    }

    @FXML
    void abrirGenerarKardex(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/generarKardex.fxml");

    }

    @FXML
    void abrirHerramientas(ActionEvent event) {
        cargarContenido("/administracion/fxml/configuracionPagosImpuestos.fxml");

    }

    @FXML
    void abrirIngresarFactura(ActionEvent event) {
        cargarContenido("/proveedores/fxml/ingresarFactura.fxml");

    }

    @FXML
    void abrirMenuAperturaCaja(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/caja/fxml/realizarAperturaDeCaja.fxml");

    }


    @FXML
    void abrirMenuArqueoCaja(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/caja/fxml/realizarArqueoDeCaja.fxml");
    }

    @FXML
    void abrirMenuAvanceCaja(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/caja/fxml/realizarAvanceDeCaja.fxml");
    }

    @FXML
    void abrirMenuCierreCaja(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/caja/fxml/realizarCierreDeCaja.fxml");
    }

    @FXML
    void abrirMenuValeCaja(ActionEvent event) {
        cargarContenido("/ventasYFacturacion/caja/fxml/realizarValeDeCaja.fxml");
    }

    @FXML
    void abrirMetasIncentivos(ActionEvent event) {
        cargarContenido("/administracion/fxml/definirIncentivosYMetas.fxml");

    }



    @FXML
    void abrirModificarProducto(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/modificarProducto.fxml");

    }

    @FXML
    void abrirDefinirPromociones(ActionEvent event) {
        cargarContenido("/administracion/fxml/definirPromocion.fxml");

    }

    @FXML
    void abrirProveedores(ActionEvent event) {
        cargarContenido("/proveedores/fxml/definirContactoProveedores.fxml");

    }

    @FXML
    void abrirRegistrarClienteN(ActionEvent event) {
        cargarContenido("/clientes/fxml/registrarClienteN.fxml");
    }

    @FXML
    void abrirRegistrarClienteJ(ActionEvent event) {
        cargarContenido("/clientes/fxml/registrarClienteJ.fxml");
    }

    @FXML
    void abrirRegistrarProducto(ActionEvent event) {
        cargarContenido("/inventarioYProductos/fxml/registrarProducto.fxml");

    }









}

