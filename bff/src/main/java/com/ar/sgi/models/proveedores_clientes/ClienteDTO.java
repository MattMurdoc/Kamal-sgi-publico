package com.ar.sgi.models.proveedores_clientes;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {

    @NotNull(message = "El id del cliente no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "La razon social del cliente no puede estar vac\u00EDa")
    @Length(max = 255, message = "La razon social del cliente no puede superar los 255 caracteres")
    private String razonSocial;

    @NotBlank(message = "El cuit del cliente no puede estar vac\u00EDa")
    @Length(max = 255, message = "El cuit del cliente no puede superar los 255 caracteres")
    private String cuit;

    @NotNull(message = "La categoria IVA del cliente no puede estar vac\u00EDa")
    private Long categoriaIVA;

    private String categoriaIVAstr;

    @NotNull(message = "El tipo de DNI no puede estar vac\u00EDa")
    private Long tipoDNI;

    private String tipoDNIstr;

    @NotBlank(message = "El domicilio del cliente no puede estar vac\u00EDo")
    @Length(max = 255, message = "El domicilio del cliente no puede superar los 255 caracteres")
    private String domicilio;

    public ClienteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Long getCategoriaIVA() {
        return categoriaIVA;
    }

    public void setCategoriaIVA(Long categoriaIVA) {
        this.categoriaIVA = categoriaIVA;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getTipoDNI() {
        return tipoDNI;
    }

    public void setTipoDNI(Long tipoDNI) {
        this.tipoDNI = tipoDNI;
    }

    public String getCategoriaIVAstr() {
        return categoriaIVAstr;
    }

    public void setCategoriaIVAstr(String categoriaIVAstr) {
        this.categoriaIVAstr = categoriaIVAstr;
    }

    public String getTipoDNIstr() {
        return tipoDNIstr;
    }

    public void setTipoDNIstr(String tipoDNIstr) {
        this.tipoDNIstr = tipoDNIstr;
    }
}