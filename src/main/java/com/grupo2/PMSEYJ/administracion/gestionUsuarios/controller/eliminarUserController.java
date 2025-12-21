package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class eliminarUserController {

    @FXML
    private Button btnBuscar;

    @FXML
    private TableColumn<?, ?> colAccion;

    @FXML
    private TableColumn<?, ?> colCorreo;

    @FXML
    private TableColumn<?, ?> colRol;

    @FXML
    private TableColumn<?, ?> colUsuario;

    @FXML
    private Label lblMensaje;

    @FXML
    private TableView<?> tablaUsuarios;

    @FXML
    private TextField txtBuscarUsuario;

    @FXML
    void buscarUsuario(ActionEvent event) {

    }

}
