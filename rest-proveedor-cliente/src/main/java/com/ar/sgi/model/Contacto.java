package com.ar.sgi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_CONTACTO")
public class Contacto extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "TELEFONO_CELULAR")
    private String celular;

    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROVEEDOR_ID")
    private Proveedor proveedor;

    public Contacto(Long id, @NotNull(message = "El nombre no puede estar vacio") String nombre,@NotNull(message = "El telefono no puede estar vacio") String telefono,@NotNull(message = "El celular no puede estar vacio") String celular, @NotNull(message = "El email no puede estar vacio") String email, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.proveedor = proveedor;
    }

    public Contacto() { }

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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
