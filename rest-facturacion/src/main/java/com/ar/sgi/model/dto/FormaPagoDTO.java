package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FormaPagoDTO {

    @NotNull(message = "El id de la forma de pago no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la forma de pago no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la forma de pago no puede superar los 255 caracteres")
    private String descripcion;

    public FormaPagoDTO() {
    }

    public FormaPagoDTO(@NotNull(message = "El id de la forma de pago no puede estar vac\u00EDo") Long id, @NotBlank(message = "La descripci\u00F3n de la forma de pago no puede estar vac\u00EDa") @Length(max = 255, message = "La descripci\u00F3n de la forma de pago no puede superar los 255 caracteres") String descripcion) {
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
