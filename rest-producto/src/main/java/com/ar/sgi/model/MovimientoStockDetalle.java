package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TB_MOVIMIENTO_STOCK_DETALLE")
public class MovimientoStockDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "MOVIMIENTO_ID")
    private MovimientoStock movimientoStock;

    @Column(name = "CANTIDAD")
    private Long cantidad;

    public MovimientoStockDetalle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public MovimientoStock getMovimientoStock() {
        return movimientoStock;
    }

    public void setMovimientoStock(MovimientoStock movimientoStock) {
        this.movimientoStock = movimientoStock;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}

