package com.ar.sgi.model.dto;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DetalleVentaDTO {

    @NotNull(message = "El id del detalle no puede estar vac\u00EDo")
    private Long id;

    @NotNull(message = "El id del detalle no puede estar vac\u00EDo")
    private Long productoId;

    @NotNull(message = "La cantidad del detalle no puede estar vac\u00EDo")
    @Min(value = 0,message = "La cantidad debe ser positiva")
    private Long cantidad;

    @NotNull(message = "El precio historico no puede estar vac\u00EDo")
    @Digits(integer=12, fraction=2)
    @Min(value = 0,message = "El precio historico debe ser positivo")
    private BigDecimal precioHistorico;

    @NotNull(message = "El id de la venta no puede estar vac\u00EDo")
    private Long venta;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(@NotNull(message = "El id del detalle no puede estar vac\u00EDo") Long id, @NotNull(message = "El id del detalle no puede estar vac\u00EDo") Long productoId, @NotNull(message = "La cantidad del detalle no puede estar vac\u00EDo") Long cantidad, @NotNull(message = "El precio historico del detalle no puede estar vac\u00EDo") BigDecimal precioHistorico, @NotNull(message = "El id de la venta no puede estar vac\u00EDo") Long venta) {
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

    public Long getVenta() {
        return venta;
    }

    public void setVenta(Long venta) {
        this.venta = venta;
    }
}
