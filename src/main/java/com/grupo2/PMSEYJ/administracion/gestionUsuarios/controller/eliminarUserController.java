package com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class eliminarUserController {

    @FXML private ComboBox<String> cmbPerfil;
    @FXML private TextField txtBuscarUsuario;
    @FXML private TableView<Object> tablaUsuarios; // Cambiar Object por tu modelo de Usuario

    /**
     * Lanza una ventana de confirmación antes de proceder.
     */
    private boolean mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Acción");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType btnAceptar = new ButtonType("Aceptar");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnAceptar, btnCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == btnAceptar;
    }

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
        String perfil = cmbPerfil.getValue();
        String nombre = txtBuscarUsuario.getText().trim();

        // Validación simple de campos vacíos
        if (perfil == null || nombre.isEmpty()) {
            mostrarAlerta("Campos Requeridos", "Debe seleccionar un perfil e ingresar un nombre para buscar.", Alert.AlertType.WARNING);
            return;
        }

        // Simulación de búsqueda exitosa
        //mostrarAlerta("Búsqueda", "Búsqueda finalizada para el perfil " + perfil, Alert.AlertType.INFORMATION);
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        // Validación: Debe seleccionar un usuario de la tabla primero
        Object seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        String perfilSeleccionado = cmbPerfil.getValue();

        if (seleccionado == null) {
            mostrarAlerta("Selección Requerida", "Debe seleccionar un usuario de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        String nombreUsuario = txtBuscarUsuario.getText(); // Simulación del nombre capturado

        // Lógica según los Casos de Uso y Perfiles
        if ("Administrador".equals(perfilSeleccionado)) {
            // Caso de Uso 1: Eliminar Administrador
            if (confirmarEliminacion(nombreUsuario, "ADMINISTRADOR")) {
                ejecutarFlujoEliminacion("Usuario administrador eliminado exitosamente");
            }
        } else if ("Auxiliar".equals(perfilSeleccionado)) {
            // Caso de Uso 2: Eliminar Auxiliar
            if (confirmarEliminacion(nombreUsuario, "AUXILIAR")) {
                ejecutarFlujoEliminacion("Usuario auxiliar eliminado exitosamente");
            }
        }
    }

    private boolean confirmarEliminacion(String usuario, String perfil) {
        return mostrarConfirmacion("¿Desea eliminar al usuario " + usuario + " con perfil " + perfil + "?");
    }

    private void ejecutarFlujoEliminacion(String mensajeExito) {
        // Aquí se simula la verificación de existencia
        boolean existe = true; // Esto se validará con la lógica compleja después

        if (existe) {
            // Escenario Básico: Eliminación exitosa
            mostrarAlerta("Éxito", mensajeExito, Alert.AlertType.INFORMATION);
            tablaUsuarios.getItems().remove(tablaUsuarios.getSelectionModel().getSelectedItem());
        } else {
            // Escenario Alternativo: El usuario no existe
            mostrarAlerta("Error", "El usuario no existe", Alert.AlertType.ERROR);
        }
    }
}
