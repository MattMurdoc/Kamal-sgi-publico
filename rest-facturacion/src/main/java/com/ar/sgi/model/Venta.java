package com.ar.sgi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "TB_VENTA")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NRO_TICKET")
    private Long nroTicket;

    @Column(name = "FECHA_HORA")
    private Timestamp fechaHora;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "CLIENTE_ID")
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name = "TARJETA_ID")
    private Tarjeta tarjeta;

    @ManyToOne
    @JoinColumn(name = "FORMA_PAGO_ID")
    private FormaPago formaPago;

    @Column(name = "NRO_CUPON")
    private String nroCupon;

    public Venta() {
    }

    public Venta(Long nroTicket, Timestamp fechaHora, BigDecimal total, Long clienteId, Tarjeta tarjeta, FormaPago formaPago, String nroCupon) {
        this.nroTicket = nroTicket;
        this.fechaHora = fechaHora;
        this.total = total;
        this.clienteId = clienteId;
        this.tarjeta = tarjeta;
        this.formaPago = formaPago;
        this.nroCupon = nroCupon;
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

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public String getNroCupon() {
        return nroCupon;
    }

    public void setNroCupon(String nroCupon) {
        this.nroCupon = nroCupon;
    }
}
