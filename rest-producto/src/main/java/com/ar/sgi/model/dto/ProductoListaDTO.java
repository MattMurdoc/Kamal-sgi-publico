package com.ar.sgi.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ProductoListaDTO {
	private Long id;
	@NotBlank(message = "La descripci\u00F3n de la familia no puede estar vac\u00EDa")
	@Length(max = 255, message = "La descripci\u00F3n de la familia no puede superar los 255 caracteres")
	private String descripcion;
	private Boolean estado;

	public ProductoListaDTO() {

	}

	public ProductoListaDTO(Long id, Boolean estado, String descripcion) {
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
