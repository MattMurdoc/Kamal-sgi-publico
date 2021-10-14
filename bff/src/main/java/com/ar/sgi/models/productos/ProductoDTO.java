package com.ar.sgi.models.productos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDTO implements Serializable {
    private Long id;

    @NotNull(message = "El codigo no puede estar vacio")
    @Length(max = 13, message = "El c\u00F3digo no puede superar los 13 caracteres")
    private String codigo;
    @Length(max = 20, message = "El c\u00F3digo interno no puede superar los 20 caracteres")
    private String codigoInterno;
    @Length(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    private String descripcion;
    private Long proveedorId;
    private String nombreFantasia;
    private Long departamentoId;
    private Long seccionId;
    private Long familiaId;
    private Long subfamiliaId;
    private Long marcaId;
    private Long unidadId;
    @Digits(integer = 12, fraction = 0)
    private int gramaje;
    private boolean estado;
    private Long productoStockId;
    private Boolean controlaStock;
    private Long stock;
    private Long stockMin;
    private Long stockMax;
    private Long puntoPedido;
    private Boolean permiteCargar;
    private Boolean permiteDescargar;
    private BigDecimal equivalenciaCarga;
    private BigDecimal equivalenciaDescarga;
    @NotNull(message = "La lista precio no puede estar vacia")
    private List<ProductoListaPrecioDTO> productoListaPrecios;
    @Digits(integer = 12, fraction = 2)
    @Range(min = 0, max = 100, message = "El impuesto iva no puede ser menor a 0 y no puede ser mayor a 100")
    private BigDecimal impuestoIva;

    public ProductoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Long getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(Long seccionId) {
        this.seccionId = seccionId;
    }

    public Long getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(Long familiaId) {
        this.familiaId = familiaId;
    }

    public Long getSubfamiliaId() {
        return subfamiliaId;
    }

    public void setSubfamiliaId(Long subfamiliaId) {
        this.subfamiliaId = subfamiliaId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Long getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(Long unidadId) {
        this.unidadId = unidadId;
    }

    public int getGramaje() {
        return gramaje;
    }

    public void setGramaje(int gramaje) {
        this.gramaje = gramaje;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getProductoStockId() {
        return productoStockId;
    }

    public void setProductoStockId(Long productoStockId) {
        this.productoStockId = productoStockId;
    }

    public Boolean getControlaStock() {
        return controlaStock;
    }

    public void setControlaStock(Boolean controlaStock) {
        this.controlaStock = controlaStock;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStockMin() {
        return stockMin;
    }

    public void setStockMin(Long stockMin) {
        this.stockMin = stockMin;
    }

    public Long getStockMax() {
        return stockMax;
    }

    public void setStockMax(Long stockMax) {
        this.stockMax = stockMax;
    }

    public Long getPuntoPedido() {
        return puntoPedido;
    }

    public void setPuntoPedido(Long puntoPedido) {
        this.puntoPedido = puntoPedido;
    }

    public Boolean getPermiteCargar() {
        return permiteCargar;
    }

    public void setPermiteCargar(Boolean permiteCargar) {
        this.permiteCargar = permiteCargar;
    }

    public Boolean getPermiteDescargar() {
        return permiteDescargar;
    }

    public void setPermiteDescargar(Boolean permiteDescargar) {
        this.permiteDescargar = permiteDescargar;
    }

    public BigDecimal getEquivalenciaCarga() {
        return equivalenciaCarga;
    }

    public void setEquivalenciaCarga(BigDecimal equivalenciaCarga) {
        this.equivalenciaCarga = equivalenciaCarga;
    }

    public BigDecimal getEquivalenciaDescarga() {
        return equivalenciaDescarga;
    }

    public void setEquivalenciaDescarga(BigDecimal equivalenciaDescarga) {
        this.equivalenciaDescarga = equivalenciaDescarga;
    }

    public List<ProductoListaPrecioDTO> getProductoListaPrecios() {
        return productoListaPrecios;
    }

    public void setProductoListaPrecios(List<ProductoListaPrecioDTO> productoListaPrecios) {
        this.productoListaPrecios = productoListaPrecios;
    }

    public BigDecimal getImpuestoIva() {
        return impuestoIva;
    }

    public void setImpuestoIva(BigDecimal impuestoIva) {
        this.impuestoIva = impuestoIva;
    }
}

