package com.ar.sgi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity(name = "TB_PRODUCTO_LISTA_PRECIO")
@IdClass(ProductoListaPrecioId.class)
public class ProductoListaPrecio implements Serializable {
	@Id
	@ManyToOne
	@JsonbTransient
	@JoinColumn(name = "PRODUCTO_ID")
	private Producto producto;
	
	@Id
	@ManyToOne
	@NotNull
	@JoinColumn(name = "LISTA_ID")
	private ProductoLista listaPrecio;


	@Digits(integer=12, fraction=2)
	@Min(value = 0,message = "El precio de compra debe ser positivo")
	@Column(name = "PRECIO_COMPRA")
	private BigDecimal  precioCompra;

	@Digits(integer=12, fraction=2)
	@Min(value = 0,message = "El precio de compra debe ser positivo")
	@Column(name = "COSTO")
	private BigDecimal  costo;

	@Digits(integer=12, fraction=2)
	@Min(value = 0,message = "El precio de compra debe ser positivo")
	@Column(name = "PRECIO_VENTA")
	private BigDecimal  precioVenta;



	public ProductoListaPrecio() {

	}

	public ProductoListaPrecio(Producto producto, ProductoLista listaPrecio, BigDecimal precioCompra, BigDecimal costo,
							   BigDecimal precioVenta) {
		this.producto = producto;
		this.listaPrecio = listaPrecio;
		this.precioCompra = precioCompra;
		this.costo = costo;
		this.precioVenta = precioVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ProductoLista getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ProductoLista listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
}
