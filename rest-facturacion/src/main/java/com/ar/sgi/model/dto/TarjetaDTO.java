package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TarjetaDTO {

    @NotNull(message = "El id de la tarjeta no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la tarjeta no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la tarjeta no puede superar los 255 caracteres")
    private String descripcion;


}
