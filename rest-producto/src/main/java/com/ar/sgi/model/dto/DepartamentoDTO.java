package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class DepartamentoDTO {
    private Long id;
    @NotBlank(message = "La descripci\u00F3n del departamento no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n del departamento no puede superar los 255 caracteres")
    private String descripcion;
    private Boolean estado;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Long id, String descripcion, Boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
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
