package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RubroDTO {

    @NotNull(message = "El id del rubro no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n del rubro no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n del rubro no puede superar los 255 caracteres")
    private String descripcion;

    private Boolean estado;

    public RubroDTO() {
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
