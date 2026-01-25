module com.grupo2.PMSEYJ {
    // Módulos JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;

    // Otros módulos comunes
    requires java.sql;
    requires java.desktop;
    requires javafx.base;



    // Abre los paquetes con controladores para FXML
    opens com.grupo2.PMSEYJ.auditoria.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.ventasYFacturacion.caja.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.ventasYFacturacion.ventas.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.administracion.gestionUsuarios.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.administracion.gestionParametros.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.clientes.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.proveedores.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.inventarioYProductos.controller to javafx.fxml;
    opens com.grupo2.PMSEYJ.administracion.dashboard to javafx.fxml;
    opens com.grupo2.PMSEYJ.app to javafx.fxml;
    opens com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto to javafx.base;
    opens com.grupo2.PMSEYJ.administracion.gestionParametros.dto to javafx.base;

    exports com.grupo2.PMSEYJ.app;


    // Exporta paquetes compartidos
    /*
    exports com.grupo2.PMSEYJ.core;
    exports com.grupo2.PMSEYJ.auditoria.dto;
    exports com.grupo2.PMSEYJ.auditoria.service;
    exports com.grupo2.PMSEYJ.auditoria.viewmodel;
    */

}