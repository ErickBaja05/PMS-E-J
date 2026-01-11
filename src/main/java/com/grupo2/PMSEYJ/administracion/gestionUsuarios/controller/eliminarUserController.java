package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class eliminarUserController implements Initializable {

    // =====================
    // CONTROLES FXML
    // =====================
    @FXML private ComboBox<String> cmbPerfil;
    @FXML private TextField txtBuscarUsuario;
    @FXML private Button btnBuscar;
    @FXML private Button btnEliminar;

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colUsuario;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, String> colRol;

    // =====================
    // DATOS
    // =====================
    private final ObservableList<Usuario> usuarios =
            FXCollections.observableArrayList();

    // =====================
    // INITIALIZE
    // =====================
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colUsuario.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colCorreo.setCellValueFactory(cell -> cell.getValue().correoProperty());
        colRol.setCellValueFactory(cell -> cell.getValue().perfilProperty());

        tablaUsuarios.setItems(usuarios);
        btnEliminar.setDisable(true);
    }

    // =====================
    // BUSCAR USUARIO
    // =====================
    @FXML
    void buscarUsuario(ActionEvent event) {

        usuarios.clear();
        btnEliminar.setDisable(true);

        String perfilSeleccionado = cmbPerfil.getValue();
        String nombreUsuario = txtBuscarUsuario.getText().trim();

        if (perfilSeleccionado == null) {
            mostrarAlerta(
                    "Error",
                    "Debe seleccionar un perfil",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (nombreUsuario.length() < 3) {
            mostrarAlerta(
                    "Error",
                    "Debe ingresar al menos 3 caracteres",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Usuario usuario = buscarUsuarioEnDB(nombreUsuario, perfilSeleccionado);

        if (usuario == null) {
            mostrarAlerta(
                    "Error",
                    "El usuario no existe",
                    Alert.AlertType.ERROR
            );
            return;
        }

        usuarios.add(usuario);
        btnEliminar.setDisable(false);
    }

    // =====================
    // ELIMINAR USUARIO
    // =====================
    @FXML
    void eliminarUsuario(ActionEvent event) {

        Usuario seleccionado =
                tablaUsuarios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Error",
                    "Debe seleccionar un usuario",
                    Alert.AlertType.WARNING
            );
            return;
        }

        String perfil = seleccionado.getPerfil();
        String nombre = seleccionado.getNombre();

        eliminarUsuarioEnDB(seleccionado);

        if (perfil.equals("ADMINISTRADOR")) {
            mostrarAlerta(
                    "Éxito",
                    "Usuario administrador eliminado exitosamente",
                    Alert.AlertType.INFORMATION
            );
            registrarAuditoria(
                    "Eliminación de usuario Administrador",
                    nombre
            );
        } else if (perfil.equals("AUXILIAR")) {
            mostrarAlerta(
                    "Éxito",
                    "Usuario auxiliar eliminado exitosamente",
                    Alert.AlertType.INFORMATION
            );
            registrarAuditoria(
                    "Eliminación de usuario Auxiliar",
                    nombre
            );
        }

        usuarios.clear();
        btnEliminar.setDisable(true);
        txtBuscarUsuario.clear();
        cmbPerfil.setValue(null);
    }

    // =====================
    // ALERTAS
    // =====================
    private void mostrarAlerta(String titulo,
                               String mensaje,
                               Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // =====================
    // SIMULACIÓN BD
    // =====================
    private Usuario buscarUsuarioEnDB(String nombre, String perfil) {

        if (perfil.equals("Administrador")
                && nombre.equalsIgnoreCase("Carlos")) {

            return new Usuario(
                    "Carlos",
                    "carlos@farmacia.com",
                    "ADMINISTRADOR"
            );
        }

        if (perfil.equals("Auxiliar")
                && nombre.equalsIgnoreCase("Ana")) {

            return new Usuario(
                    "Ana",
                    "ana@farmacia.com",
                    "AUXILIAR"
            );
        }

        return null;
    }

    private void eliminarUsuarioEnDB(Usuario usuario) {
        // Simulación eliminación
    }

    private void registrarAuditoria(String accion,
                                    String usuarioEliminado) {

        String usuarioActual = "adminActual";
        LocalDateTime fechaHora = LocalDateTime.now();

        System.out.println("MÓDULO: Administración");
        System.out.println("ACCIÓN: " + accion);
        System.out.println("FECHA/HORA: " + fechaHora);
        System.out.println("USUARIO ELIMINADO: " + usuarioEliminado);
        System.out.println("REALIZADO POR: " + usuarioActual);
    }
}
