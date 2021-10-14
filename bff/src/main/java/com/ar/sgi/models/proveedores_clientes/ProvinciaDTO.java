package com.ar.sgi.models.proveedores_clientes;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProvinciaDTO {

    @NotNull(message = "El id de la provincia no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la provincia no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la provincia no puede superar los 255 caracteres")
    private String descripcion;

    public ProvinciaDTO() {
    }

    public ProvinciaDTO(@NotNull(message = "El id de la provincia no puede estar vacio") Long id, @NotBlank(message = "La descripcion de la provincia no puede estar vacia") @Length(max = 255, message = "La descripcion de la provincia no puede superar los 255 caracteres") String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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


