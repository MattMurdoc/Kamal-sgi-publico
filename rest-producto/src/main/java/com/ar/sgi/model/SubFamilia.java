package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_SUBFAMILIA")
public class SubFamilia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la subfamilia no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la subfamilia no puede superar los 255 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @NotNull(message = "Debe especificar una familia")
    @ManyToOne
    @JoinColumn(name = "FAMILIA_ID")
    private Familia familia;

    @Column(name = "ESTADO")
    private Boolean estado;

    public SubFamilia() {
    }

    public SubFamilia(Long id, String descripcion, Boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
