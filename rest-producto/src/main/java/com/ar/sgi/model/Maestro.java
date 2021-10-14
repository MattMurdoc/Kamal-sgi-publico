package com.ar.sgi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TB_MAESTRO")
public class Maestro implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "VALOR")
	private Long valor;

	@Column(name = "TIPO_DATO")
	private String tipoDato;

	@Column(name = "FILTRO")
	private Long filtro;

	@Column(name = "DESCRIPCION_FILTRO")
	private String descripcionFiltro;

	public Maestro() {

	}

	public Maestro(Long id, String descripcion, Long valor, String tipoDato, Long filtro, String descripcionFiltro) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
		this.tipoDato = tipoDato;
		this.filtro = filtro;
		this.descripcionFiltro = descripcionFiltro;
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

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Long getFiltro() {
		return filtro;
	}

	public void setFiltro(Long filtro) {
		this.filtro = filtro;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}
}
