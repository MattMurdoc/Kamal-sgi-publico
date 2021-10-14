package com.ar.sgi.model.dto;

import com.ar.sgi.model.Rol;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El usuario no puede estar vac\u00EDa")
    @Length(max = 255, message = "El usuario no puede superar los 255 caracteres")
    private String usuario;

    @NotBlank(message = "El nombre del usuario no puede estar vac\u00EDa")
    @Length(max = 255, message = "El nombre del usuario no puede superar los 255 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido del usuario no puede estar vac\u00EDa")
    @Length(max = 255, message = "El apellido del usuario no puede superar los 255 caracteres")
    private String apellido;

    @NotBlank(message = "La contraseña del usuario no puede estar vac\u00EDa")
    @Length(max = 255, message = "La contraseña del usuario no puede superar los 255 caracteres")
    private String password;

    @NotNull(message = "El usuario no puede estar vac\u00EDa")
    private Long rolId;

    private Boolean estado;

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
