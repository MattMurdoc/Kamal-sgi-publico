package com.ar.sgi.models.proveedores_clientes;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProveedorDTO {

    @NotNull(message = "El id del proveedor no puede estar vac\u00EDo")
    private Long id;

    @NotBlank(message = "El cuit del proveedor no puede estar vac\u00EDa")
    @Length(max = 255, message = "El cuit del proveedor no puede superar los 255 caracteres")
    private String cuit;

    @NotBlank(message = "La razon social del proveedor no puede estar vac\u00EDa")
    @Length(max = 255, message = "La razon social del proveedor no puede superar los 255 caracteres")
    private String razonSocial;

    @NotBlank(message = "El nombre fantasia del proveedor no puede estar vac\u00EDa")
    @Length(max = 255, message = "El nombre fantasia del proveedor no puede superar los 255 caracteres")
    private String nombreFantasia;

    @Length(max = 255, message = "El domicilio del proveedor no puede superar los 255 caracteres")
    private String domicilio;

    @Length(max = 255, message = "El telefono del proveedor no puede superar los 255 caracteres")
    private String telefono;

    @Length(max = 255, message = "El celular del proveedor no puede superar los 255 caracteres")
    private String celular;

    @Length(max = 255, message = "El email del proveedor no puede superar los 255 caracteres")
    private String email;

    @Length(max = 255, message = "La pagina del proveedor no puede superar los 255 caracteres")
    private String pagina;

    private Long provinciaId;

    private Long localidadId;

    private Long rubroId;

    private List<ContactoDTO> contactos;

    public ProveedorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }

    public Long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Long localidadId) {
        this.localidadId = localidadId;
    }

    public Long getRubroId() {
        return rubroId;
    }

    public void setRubroId(Long rubroId) {
        this.rubroId = rubroId;
    }

    public List<ContactoDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoDTO> contactos) {
        this.contactos = contactos;
    }
}
