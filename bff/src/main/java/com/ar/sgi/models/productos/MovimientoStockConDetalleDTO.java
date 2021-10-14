package com.ar.sgi.models.productos;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MovimientoStockConDetalleDTO {
    private Long id;
    @NotNull
    private Long tipoMovimiento;
    @NotNull
    private Long motivo;
    private String observacion;
    private List<MovimientoStockDetalleDTO> detalle;

    public MovimientoStockConDetalleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<MovimientoStockDetalleDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<MovimientoStockDetalleDTO> detalle) {
        this.detalle = detalle;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
