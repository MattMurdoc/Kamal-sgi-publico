package com.ar.sgi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "TB_VENTA")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NRO_TICKET")
    private Long nroTicket;

    @Column(name = "FECHA_HORA")
    private Timestamp fechaHora;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "CLIENTE_ID")
    private Long clienteId;

    @Column(name = "TIPO_FAC")
    private String tipoFac;

    @Column(name = "PUNTO_VENTA")
    private Long puntoVenta;

    public Venta() {
    }

    public Long getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(Long nroTicket) {
        this.nroTicket = nroTicket;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoFac() {
        return tipoFac;
    }

    public void setTipoFac(String tipoFac) {
        this.tipoFac = tipoFac;
    }

    public Long getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(Long puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
