package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto.InfoUsuarioDTO;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioService;
import com.grupo2.PMSEYJ.administracion.gestionUsuarios.service.UsuarioServiceImpl;
import com.grupo2.PMSEYJ.core.session.SesionActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class eliminarUserController implements Initializable {


    @FXML private TextField txtBuscarUsuario;
    @FXML private TableView<InfoUsuarioDTO> tablaUsuarios;
    @FXML
    private TableColumn<InfoUsuarioDTO, String> colCorreo;

    @FXML
    private TableColumn<InfoUsuarioDTO, String> colRol;

    @FXML
    private TableColumn<InfoUsuarioDTO, String> colUsuario;

    private ObservableList<InfoUsuarioDTO> listaUsuarios;

    private UsuarioService usuarioService;
    private InfoUsuarioDTO usuarioConsultado;


    /**
     * Lanza una ventana informativa para éxito o error.
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void buscarUsuario(ActionEvent event) {
        if(usuarioConsultado!=null){
            listaUsuarios.remove(usuarioConsultado);
        }

        String nombre = txtBuscarUsuario.getText().trim();

        // Validación simple de campos vacíos
        if (nombre.isEmpty()) {
            mostrarAlerta("Campos Requeridos", "Debe ingresar un nombre de usuario para buscar.", Alert.AlertType.WARNING);
            return;
        }


        try{
            InfoUsuarioDTO infoUsuario = usuarioService.consultarUsuarioPorNombre(nombre);
            usuarioConsultado = infoUsuario;
            listaUsuarios = FXCollections.observableArrayList();
            listaUsuarios.add(infoUsuario);
            tablaUsuarios.setItems(listaUsuarios);
            mostrarAlerta("Búsqueda", "Usuario encontrado. Puede ver su información en la tabla", Alert.AlertType.INFORMATION);

        }catch(IllegalArgumentException e){
            mostrarAlerta("No existe usuario", e.getMessage(), Alert.AlertType.ERROR);
        }


    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        // Validación: Debe seleccionar un usuario de la tabla primero
        InfoUsuarioDTO seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Selección Requerida", "Debe seleccionar un usuario de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        String nombreUsuario = seleccionado.getNombre_us();
        if(nombreUsuario.equals(SesionActual.getUsuario().getNombre_us())){
            mostrarAlerta("Error de eliminación", "No puede eliminar el usuario con el que inició sesión. Ingrese desde otro usuario e intente nuevamente", Alert.AlertType.ERROR);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Usuario");
        alert.setHeaderText("Confirmación de eliminación");
        alert.setContentText("Desea eliminar al usuario: " + nombreUsuario);
        if(alert.showAndWait().get() == ButtonType.OK){
            ejecutarFlujoEliminacion("Usuario: " + usuarioConsultado.getNombre_us()+ " Eliminado con éxito");
            listaUsuarios.remove(usuarioConsultado);
        }else{
            mostrarAlerta("Operación cancelada", "Se canceló la operación de eliminación", Alert.AlertType.INFORMATION);
        }





    }



    private void ejecutarFlujoEliminacion(String mensajeExito) {
        if(usuarioConsultado != null){
            usuarioService.eliminarUsuario(usuarioConsultado);
            mostrarAlerta("Eliminación exitosa", mensajeExito, Alert.AlertType.INFORMATION);
        }else{
            mostrarAlerta("No se ha consultado un usuario", "Debe consultar un usuario para eliminarlo.", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioService = new UsuarioServiceImpl();
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre_us"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo_us"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("perfil"));
    }
}
