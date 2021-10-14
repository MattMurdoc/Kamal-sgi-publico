package com.ar.sgi.models.productos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class VentaDTO {

    @NotNull(message = "El nro de ticket no puede estar vac\u00EDo")
    private Long nroTicket;
    @NotNull(message = "El total no puede estar vac\u00EDo")
    @Digits(integer=12, fraction=2)
    @Min(value = 0,message = "El precio total debe ser positivo")
    private BigDecimal total;
    private Long clienteId;
    private String tipoFac;
    @NotNull(message = "El punto de venta no puede estar vac\u00EDo")
    private Long puntoVenta;
    private List<DetalleVentaDTO> detalle;

    public Long getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(Long nroTicket) {
        this.nroTicket = nroTicket;
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

    public List<DetalleVentaDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleVentaDTO> detalle) {
        this.detalle = detalle;
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
}
