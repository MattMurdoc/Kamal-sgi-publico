package com.ar.sgi.model;

public class Respuesta {

    private String mensaje;

    public Respuesta() {
    }

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}