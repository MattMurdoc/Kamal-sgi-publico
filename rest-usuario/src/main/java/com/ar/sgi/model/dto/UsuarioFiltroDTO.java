package com.ar.sgi.model.dto;

public class UsuarioFiltroDTO {

    String usuario,nombre,apellido;
    Long rol;
    Boolean estado;

    public UsuarioFiltroDTO(){
    }

    public String getUsuario(){
        return usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public Long getRol(){
        return rol;
    }
    public void setRol(Long rol){
        this.rol = rol;
    }

    public Boolean getEstado(){
        return estado;
    }
    public void setEstado(Boolean estado){
        this.estado = estado;
    }
}