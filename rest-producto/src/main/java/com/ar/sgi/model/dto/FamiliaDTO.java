package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FamiliaDTO {
    private Long id;
    @NotBlank(message = "La descripci\u00F3n de la familia no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la familia no puede superar los 255 caracteres")
    private String descripcion;
    @NotNull(message = "Debe especificar una seccion")
    private Long seccionId;
    private Boolean estado;

    public FamiliaDTO() {
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

    public Long getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(Long seccionId) {
        this.seccionId = seccionId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
