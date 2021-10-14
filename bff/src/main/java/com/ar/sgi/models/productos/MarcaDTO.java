package com.ar.sgi.models.productos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class MarcaDTO {
	private Long id;
	@NotBlank(message = "La descripci\u00F3n de la marca no puede estar vac\u00EDa")
	@Length(max = 255, message = "La descripci\u00F3n de la marca no puede superar los 255 caracteres")
	private String descripcion;
	private Boolean estado;

	public MarcaDTO() {

	}

	public MarcaDTO(Long id, String descripcion, Boolean estado) {
		super();
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
