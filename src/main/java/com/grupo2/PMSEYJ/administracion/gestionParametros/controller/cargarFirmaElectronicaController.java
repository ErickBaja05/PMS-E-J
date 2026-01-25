package com.grupo2.PMSEYJ.administracion.gestionParametros.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Optional;

public class cargarFirmaElectronicaController {

    @FXML private Button btnCancelar, btnCargar, btnExplorar;
    @FXML private Label lblEstado;
    @FXML private TextField txtRutaArchivo;

    private File archivoSeleccionado;

    /**
     * Lanza una ventana emergente para notificaciones o confirmaciones.
     */
    private boolean mostrarDialogo(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        if (tipo == Alert.AlertType.CONFIRMATION) {
            ButtonType btnAceptar = new ButtonType("Aceptar");
            ButtonType btnCancelarDiag = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnAceptar, btnCancelarDiag);
            Optional<ButtonType> result = alert.showAndWait();
            return result.isPresent() && result.get() == btnAceptar;
        } else {
            alert.showAndWait();
            return true;
        }
    }

    @FXML
    void explorarArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Firma Electrónica");

        // Filtro para mostrar solo archivos .p12 inicialmente
        //fileChooser.getExtensionFilters().add(
          //      new FileChooser.ExtensionFilters("Firma Electrónica (*.p12)", "*.p12")
        //);

        Stage stage = (Stage) btnExplorar.getScene().getWindow();
        archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            txtRutaArchivo.setText(archivoSeleccionado.getAbsolutePath());
        }
    }

    @FXML
    void procesarFirma(ActionEvent event) {
        // ESCENARIO ALTERNATIVO 1: Verificar que se haya proporcionado un archivo
        if (archivoSeleccionado == null) {
            mostrarDialogo("Error", "Error de carga. Cargue un archivo antes de continuar", Alert.AlertType.WARNING);
            return;
        }

        // ESCENARIO ALTERNATIVO 2: Validar extensión .p12
        if (!archivoSeleccionado.getName().toLowerCase().endsWith(".p12")) {
            mostrarDialogo("Error", "Extensión No Permitida. Cargue un archivo de firma electrónica (.p12)", Alert.AlertType.ERROR);
            return;
        }

        // ESCENARIO ALTERNATIVO 3: Validar que el tamaño sea mayor a cero
        if (archivoSeleccionado.length() == 0) {
            mostrarDialogo("Archivo Inválido", "El archivo cargado está vacío", Alert.AlertType.ERROR);
            return;
        }

        // Ventana de Confirmación antes de registrar
        if (mostrarDialogo("Confirmar Registro", "¿Desea registrar esta firma electrónica en el sistema?", Alert.AlertType.CONFIRMATION)) {

            // ESCENARIO ALTERNATIVO 4: Verificar si ya existe (Simulado)
            if (verificarSiYaExiste(archivoSeleccionado.getName())) {
                mostrarDialogo("Registro Duplicado", "El archivo cargado ya ha sido registrado anteriormente", Alert.AlertType.ERROR);
                return;
            }

            // ESCENARIO BÁSICO: Registro exitoso
            registrarFirma();
            mostrarDialogo("Éxito", "La firma fue registrada correctamente.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
        }
    }

    @FXML
    void cancelarAccion(ActionEvent event) {
        limpiarFormulario();
        mostrarDialogo("Cancelado", "La operación ha sido cancelada.", Alert.AlertType.INFORMATION);
    }

    private void limpiarFormulario() {
        archivoSeleccionado = null;
        txtRutaArchivo.clear();
    }

    // --- MÉTODOS SIMPLIFICADOS (Lógica Simple) ---

    private boolean verificarSiYaExiste(String nombreArchivo) {
        // Simulación de validación de registro previo
        return false;
    }

    private void registrarFirma() {
        // Aquí iría la lógica para copiar el archivo o guardar la ruta en el sistema
        System.out.println("Firma registrada: " + archivoSeleccionado.getName());
    }
}
