package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "PERMISO_ID")
    private Rol rol;

    public Permiso() {
    }

    public Permiso(Long id,
                   @NotBlank(message = "La descripci\u00F3n del rol no puede estar vac\u00EDa")
                   @Length(max = 255, message = "La descripci\u00F3n del rol no puede superar los 255 caracteres") String descripcion,
                   Rol rol) {
        this.id = id;
        this.descripcion = descripcion;
        this.rol = rol;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
