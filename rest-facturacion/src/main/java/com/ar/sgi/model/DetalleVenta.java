package com.ar.sgi.model;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "TB_DETALLEVENTA")
public class DetalleVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCTO_ID")
    private Long productoId;

    @Column(name = "CANTIDAD")
    private Long cantidad;

    @Column(name = "PRECIO_HISTORICO")
    private BigDecimal precioHistorico;

    @JoinColumn(name = "VENTA_ID")
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, Long productoId, Long cantidad, BigDecimal precioHistorico, Venta venta) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioHistorico = precioHistorico;
        this.venta = venta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioHistorico() {
        return precioHistorico;
    }

    public void setPrecioHistorico(BigDecimal precioHistorico) {
        this.precioHistorico = precioHistorico;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
