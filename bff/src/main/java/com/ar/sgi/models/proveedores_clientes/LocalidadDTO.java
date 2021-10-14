package com.ar.sgi.models.proveedores_clientes;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocalidadDTO {

    @NotNull(message = "El id de la localidad no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la localidad no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la localidad no puede superar los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El id de la provincia no puede estar vac\u00EDo")
    private Long provinciaId;

    public LocalidadDTO() {
    }

    public LocalidadDTO(@NotNull(message = "El id de la localidad no puede estar vac\u00EDo") Long id, @NotBlank(message = "La descripci\u00F3n de la localidad no puede estar vac\u00EDa") @Length(max = 255, message = "La descripci\u00F3n de la localidad no puede superar los 255 caracteres") String descripcion, @NotNull(message = "El id de la provincia no puede estar vac\u00EDo") Long provinciaId) {
        this.id = id;
        this.descripcion = descripcion;
        this.provinciaId = provinciaId;
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

    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }
}
