package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_FAMILIA")
public class Familia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la familia no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la familia no puede superar los 255 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @NotNull(message = "Debe especificar una seccion")
    @ManyToOne
    @JoinColumn(name = "SECCION_ID")
    private Seccion seccion;

    @Column(name = "ESTADO")
    private Boolean estado;


    public Familia() {
    }

    public Familia(Long id, String descripcion, Boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.seccion = seccion;
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

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
