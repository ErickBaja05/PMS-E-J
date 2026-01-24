package com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto;

public class NuevoUsuarioDTO {

    private String nombre;
    private String correo;
    private String password;
    private String perfil;

    public NuevoUsuarioDTO() {}
    public NuevoUsuarioDTO(String nombre, String correo, String password, String perfil) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.perfil = perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
