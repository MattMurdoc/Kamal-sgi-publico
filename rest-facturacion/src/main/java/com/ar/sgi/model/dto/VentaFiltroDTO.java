package com.ar.sgi.model.dto;

import java.sql.Timestamp;

public class VentaFiltroDTO {

    Timestamp fechaDesde, fechaHasta;
    Long formaPago;

    public VentaFiltroDTO() {
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Long getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Long formaPago) {
        this.formaPago = formaPago;
    }
}
