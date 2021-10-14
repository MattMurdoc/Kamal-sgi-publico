package com.ar.sgi.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "TB_PRODUCTO_LISTA")
public class ProductoLista implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ESTADO")
	private Boolean estado;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	public ProductoLista() {

	}

	public ProductoLista(Long id, Boolean estado, String descripcion) {
		this.id = id;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
