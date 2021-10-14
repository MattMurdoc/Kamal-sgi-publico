package com.ar.sgi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class MovimientoStockFiltroDTO {
    private String fechaDesdeStr;
    private String fechaHastaStr;
    private Long tipoMovimiento;
    private Long motivo;
    @JsonIgnore
    private Timestamp fechaDesde;
    @JsonIgnore
    private Timestamp fechaHasta;

    public MovimientoStockFiltroDTO() {
    }

    public String getFechaDesdeStr() {
        return fechaDesdeStr;
    }

    public void setFechaDesdeStr(String fechaDesdeStr) {
        this.fechaDesdeStr = fechaDesdeStr;
    }

    public String getFechaHastaStr() {
        return fechaHastaStr;
    }

    public void setFechaHastaStr(String fechaHastaStr) {
        this.fechaHastaStr = fechaHastaStr;
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

    public Long getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Long tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Long getMotivo() {
        return motivo;
    }

    public void setMotivo(Long motivo) {
        this.motivo = motivo;
    }
}
