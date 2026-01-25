package com.grupo2.PMSEYJ.administracion.gestionUsuarios.dto;

public class InfoUsuarioDTO {
    private String nombre_us;
    private String correo_us;
    private String perfil;

    public InfoUsuarioDTO(String nombre_us, String correo_us, String perfil) {
        this.nombre_us = nombre_us;
        this.correo_us = correo_us;
        this.perfil = perfil;

    }
    public InfoUsuarioDTO() {}

    public String getNombre_us() {
        return nombre_us;
    }

    public void setNombre_us(String nombre_us) {
        this.nombre_us = nombre_us;
    }

    public String getCorreo_us() {
        return correo_us;
    }

    public void setCorreo_us(String correo_us) {
        this.correo_us = correo_us;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
