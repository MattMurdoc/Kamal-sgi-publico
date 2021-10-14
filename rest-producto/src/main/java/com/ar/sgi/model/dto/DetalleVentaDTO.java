package com.ar.sgi.model.dto;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DetalleVentaDTO {

    @NotNull(message = "El id del producto no puede estar vac\u00EDo")
    private Long productoId;

    @NotNull(message = "La cantidad del detalle no puede estar vac\u00EDo")
    @Min(value = 0,message = "La cantidad debe ser positiva")
    private Long cantidad;

    @NotNull(message = "El precio historico no puede estar vac\u00EDo")
    @Digits(integer=12, fraction=2)
    @Min(value = 0,message = "El precio historico debe ser positivo")
    private BigDecimal precioHistorico;

    private Long nroTicket;

    public DetalleVentaDTO() {
    }

    public Long getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(Long nroTicket) {
        this.nroTicket = nroTicket;
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
}
