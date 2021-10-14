package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubFamiliaDTO {
    private Long id;
    @NotBlank(message = "La descripci\u00F3n de la subfamilia no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la subfamilia no puede superar los 255 caracteres")
    private String descripcion;
    @NotNull(message = "Debe especificar una familia")
    private Long familiaId;
    private Boolean estado;

    public SubFamiliaDTO() {
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

    public Long getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(Long familiaId) {
        this.familiaId = familiaId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

