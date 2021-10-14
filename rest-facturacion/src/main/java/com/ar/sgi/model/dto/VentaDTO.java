package com.ar.sgi.model.dto;

import com.ar.sgi.model.FormaPago;
import com.ar.sgi.model.Tarjeta;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class VentaDTO {

    @NotNull(message = "El nro de ticket no puede estar vac\u00EDo")
    private Long nroTicket;

    private Timestamp fechaHora;

    @NotNull(message = "El total no puede estar vac\u00EDo")
    @Digits(integer=12, fraction=2)
    @Min(value = 0,message = "El precio total debe ser positivo")
    private BigDecimal total;

    @NotNull(message = "El cliente no puede estar vac\u00EDo")
    private Long clienteId;

    @NotNull(message = "La tarjeta no puede estar vac\u00EDo")
    private Long tarjeta;

    @NotNull(message = "La forma de pago no puede estar vac\u00EDo")
    private Long formaPago;

    @NotBlank(message = "El numero de cupon no puede estar vac\u00EDa")
    @Length(max = 255, message = "El numero de cupon no puede superar los 255 caracteres")
    private String nroCupon;

}
