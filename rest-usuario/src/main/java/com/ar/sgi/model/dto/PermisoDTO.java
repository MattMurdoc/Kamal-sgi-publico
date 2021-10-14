package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PermisoDTO {

    private Long id;

    @NotBlank(message = "La descripci\u00F3n del permiso no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la localidad no puede superar los 255 caracteres")
    private String descripcion;

    private boolean estado;

    @NotNull(message = "El rol no puede estar vacío")
    private Long idRol;

    public PermisoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
}
