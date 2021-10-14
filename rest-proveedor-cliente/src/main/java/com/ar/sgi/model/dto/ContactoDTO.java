package com.ar.sgi.model.dto;

import com.ar.sgi.model.Proveedor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactoDTO {

    @NotNull(message = "El id del cliente no puede estar vacio")
    private Long id;

    @NotBlank(message = "El nombre del contacto no puede estar vac\u00EDo")
    @Length(max = 255, message = "el nombre del contacto no puede superar los 255 caracteres")
    private String nombre;

    @Length(max = 255, message = "El telefono del contacto no puede superar los 255 caracteres")
    private String telefono;

    @Length(max = 255, message = "El celular del contacto no puede superar los 255 caracteres")
    private String celular;

    @Length(max = 255, message = "El mail del contacto no puede superar los 255 caracteres")
    private String email;

    @Length(max = 255, message = "El proveedor del contacto no puede superar los 255 caracteres")
    private Long proveedor;

    public ContactoDTO() {
    }

    public ContactoDTO(@NotNull(message = "El id del cliente no puede estar vacio") Long id, @NotBlank(message = "El nombre del contacto no puede estar vac\u00EDo") @Length(max = 255, message = "el nombre del contacto no puede superar los 255 caracteres") String nombre, @NotBlank(message = "El telefono del contacto no puede estar vac\u00EDo") @Length(max = 255, message = "El telefono del contacto no puede superar los 255 caracteres") String telefono, @NotBlank(message = "El celular del contacto no puede estar vac\u00EDo") @Length(max = 255, message = "El celular del contacto no puede superar los 255 caracteres") String celular, @NotBlank(message = "El mail del contacto no puede estar vac\u00EDo") @Length(max = 255, message = "El mail del contacto no puede superar los 255 caracteres") String email, @NotBlank(message = "El proveedor del contacto no puede estar vac\u00EDo") @Length(max = 255, message = "El proveedor del contacto no puede superar los 255 caracteres") Long proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.proveedor = proveedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProveedor() {
        return proveedor;
    }

    public void setProveedor(Long proveedor) {
        this.proveedor = proveedor;
    }
}
