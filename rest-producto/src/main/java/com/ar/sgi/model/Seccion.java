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

@Entity(name = "TB_SECCION")
public class Seccion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "La descripci\u00F3n de la seccion no puede estar vac\u00EDa")
    @Length(max = 255, message = "La descripci\u00F3n de la familia no puede superar los 255 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @NotNull(message = "Debe especificar un departamento")
    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID")
    private Departamento departamento;

    @Column(name = "ESTADO")
    private Boolean estado;


    public Seccion() {
    }

    public Seccion(Long id, String descripcion, Familia familia, Boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.departamento = departamento;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
