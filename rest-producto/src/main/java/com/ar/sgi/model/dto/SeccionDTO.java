package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SeccionDTO implements Serializable {
    private Long id;
    @NotBlank(message = "La descripci\u00F3n de la seccion no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la sección no puede superar los 255 caracteres")
    private String descripcion;
    @NotNull(message = "Debe especificar un departamento")
    private Long departamentoId;
    private Boolean estado;

    public SeccionDTO() {
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

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

