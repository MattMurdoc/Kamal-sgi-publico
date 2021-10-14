package com.ar.sgi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_LOCALIDAD")
public class Localidad extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "PROVINCIA_ID")
    private Provincia provincia;

    public Localidad(Long id, String descripcion, @NotNull(message = "Debe especificar una provincia") Provincia provincia) {
        this.id = id;
        this.descripcion = descripcion;
        this.provincia = provincia;
    }

    public Localidad() {
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
