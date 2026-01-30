package com.grupo2.PMSEYJ.proveedores.model;

public class Proveedor {

    Integer id_prove;
    String nombre_pro;
    String telefono_pro;
    String correo_pro;

    public Proveedor() {

    }

    public Proveedor(Integer id_prove, String nombre_pro, String telefono_pro, String correo_pro) {
        this.id_prove = id_prove;
        this.nombre_pro = nombre_pro;
        this.telefono_pro = telefono_pro;
        this.correo_pro = correo_pro;
    }

    public Integer getId_prove() {
        return id_prove;
    }

    public void setId_prove(Integer id_prove) {
        this.id_prove = id_prove;
    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public String getTelefono_pro() {
        return telefono_pro;
    }

    public void setTelefono_pro(String telefono_pro) {
        this.telefono_pro = telefono_pro;
    }

    public String getCorreo_pro() {
        return correo_pro;
    }

    public void setCorreo_pro(String correo_pro) {
        this.correo_pro = correo_pro;
    }
}
