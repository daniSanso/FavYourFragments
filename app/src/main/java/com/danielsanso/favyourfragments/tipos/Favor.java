package com.danielsanso.favyourfragments.tipos;

/**
 * Created by danielsanso on 10/3/18.
 */

public class Favor {
    String titulo;
    String comentario;
    String usuario;

    public Favor(String titulo, String comentario, String usuario) {
        this.titulo = titulo;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public Favor() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
