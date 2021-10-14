package com.ar.sgi.models.usuarios;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class RolDTO {

    private Long id;

    @NotBlank(message = "La descripci\u00F3n del rol no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la localidad no puede superar los 255 caracteres")
    private String descripcion;

    public RolDTO() {
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
}
