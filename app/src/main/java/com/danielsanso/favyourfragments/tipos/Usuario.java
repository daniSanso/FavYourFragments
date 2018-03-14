package com.danielsanso.favyourfragments.tipos;

/**
 * Created by danielsanso on 10/3/18.
 */

public class Usuario {
    String nombre;
    String bloque;
    String piso;

    public Usuario(String nombre, String bloque, String piso) {

        this.nombre = nombre;
        this.bloque = bloque;
        this.piso = piso;
    }

    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }
}
