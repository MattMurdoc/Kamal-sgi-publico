package com.ar.sgi.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductoListaPrecioDTO implements Serializable {
	private Long listaPrecioId;
	@Digits(integer = 12, fraction = 2)
	@Min(value = 0, message = "El precio de compra debe ser positivo")
	private BigDecimal precioCompra;
	@Digits(integer = 12, fraction = 2)
	@Min(value = 0, message = "El precio de compra debe ser positivo")
	private BigDecimal costo;
	@Digits(integer = 12, fraction = 2)
	@Min(value = 0, message = "El precio de compra debe ser positivo")
	private BigDecimal precioVenta;

	public ProductoListaPrecioDTO() {

	}

	public Long getListaPrecioId() {
		return listaPrecioId;
	}

	public void setListaPrecioId(Long listaPrecioId) {
		this.listaPrecioId = listaPrecioId;
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

