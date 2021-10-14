package com.ar.sgi.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductoListaPrecioId implements Serializable {
	
	private Long producto;
	private Long listaPrecio;

	public ProductoListaPrecioId() {

	}

	public ProductoListaPrecioId(Long producto, Long listaPrecio) {
		super();
		this.producto = producto;
		this.listaPrecio = listaPrecio;
	}

	public Long getProducto() {
		return producto;
	}

	public void setProducto(Long producto) {
		this.producto = producto;
	}

	public Long getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(Long listaPrecio) {
		this.listaPrecio = listaPrecio;
	}
}
