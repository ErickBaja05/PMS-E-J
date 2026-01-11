package com.grupo2.PMSEYJ.administracion.gestionUsuarios.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

    private final StringProperty nombre;
    private final StringProperty correo;
    private final StringProperty perfil;

    public Usuario(String nombre, String correo, String perfil) {
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.perfil = new SimpleStringProperty(perfil);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public String getPerfil() {
        return perfil.get();
    }

    public StringProperty perfilProperty() {
        return perfil;
    }
}
