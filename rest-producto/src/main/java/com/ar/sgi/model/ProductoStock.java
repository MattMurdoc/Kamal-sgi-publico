package com.ar.sgi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "TB_PRODUCTO_STOCK")
public class ProductoStock implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CONTROLA_STOCK")
	private Boolean controlaStock;

	@Column(name = "STOCK")
	private Long stock;

	@Column(name = "STOCK_MIN")
	private Long stockMin;

	@Column(name = "STOCK_MAX")
	private Long stockMax;

	@Column(name = "PUNTO_PEDIDO")
	private Long puntoPedido;

	@Column(name = "PERMITE_CARGAR")
	private Boolean permiteCargar;

	@Column(name = "PERMITE_DESCARGAR")
	private Boolean permiteDescargar;

	@Column(name = "EQUIVALENCIA_CARGA")
	private BigDecimal equivalenciaCarga;

	@Column(name = "EQUIVALENCIA_DESCARGA")
	private BigDecimal equivalenciaDescarga;

	public ProductoStock() {
	}

	public ProductoStock(Long id, Boolean controlaStock, Long stock, Long stockMin, Long stockMax,
						 Long puntoPedido, Boolean permiteCargar, Boolean permiteDescargar, BigDecimal equivalenciaCarga,
						 BigDecimal equivalenciaDescarga) {
		this.id = id;
		this.controlaStock = controlaStock;
		this.stock = stock;
		this.stockMin = stockMin;
		this.stockMax = stockMax;
		this.puntoPedido = puntoPedido;
		this.permiteCargar = permiteCargar;
		this.permiteDescargar = permiteDescargar;
		this.equivalenciaCarga = equivalenciaCarga;
		this.equivalenciaDescarga = equivalenciaDescarga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getControlaStock() {
		return controlaStock;
	}

	public void setControlaStock(Boolean controlaStock) {
		this.controlaStock = controlaStock;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getStockMin() {
		return stockMin;
	}

	public void setStockMin(Long stockMin) {
		this.stockMin = stockMin;
	}

	public Long getStockMax() {
		return stockMax;
	}

	public void setStockMax(Long stockMax) {
		this.stockMax = stockMax;
	}

	public Long getPuntoPedido() {
		return puntoPedido;
	}

	public void setPuntoPedido(Long puntoPedido) {
		this.puntoPedido = puntoPedido;
	}

	public Boolean getPermiteCargar() {
		return permiteCargar;
	}

	public void setPermiteCargar(Boolean permiteCargar) {
		this.permiteCargar = permiteCargar;
	}

	public Boolean getPermiteDescargar() {
		return permiteDescargar;
	}

	public void setPermiteDescargar(Boolean permiteDescargar) {
		this.permiteDescargar = permiteDescargar;
	}

	public BigDecimal getEquivalenciaCarga() {
		return equivalenciaCarga;
	}

	public void setEquivalenciaCarga(BigDecimal equivalenciaCarga) {
		this.equivalenciaCarga = equivalenciaCarga;
	}

	public BigDecimal getEquivalenciaDescarga() {
		return equivalenciaDescarga;
	}

	public void setEquivalenciaDescarga(BigDecimal equivalenciaDescarga) {
		this.equivalenciaDescarga = equivalenciaDescarga;
	}
}
