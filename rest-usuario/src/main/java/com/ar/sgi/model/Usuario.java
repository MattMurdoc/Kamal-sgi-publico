package com.ar.sgi.model;

import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private Rol rol;

    @Column(name = "ESTADO")
    private Boolean estado;

    public Usuario() {
    }

    public Usuario(Long id, @NotNull(message = "El usuario no puede estar vacio") @Length(max = 25, message = "El usuario no puede superar los 25 caracteres") String usuario, @NotNull(message = "El nombre no puede estar vacio") String nombre, @NotNull(message = "El apellido no puede estar vacio") String apellido,@NotNull(message = "La contrase\u00F1a no puede estar vacia") String password, @NotNull(message = "El rol no puede estar vacio") Rol rol, boolean estado ) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


}