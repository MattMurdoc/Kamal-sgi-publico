package com.ar.sgi.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;

@Entity(name = "TB_PRODUCTO")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "El codigo no puede estar vacio")
    @Length(max = 13, message = "El c\u00F3digo no puede superar los 13 caracteres")
    @Column(name = "CODIGO")
    private String codigo;

    @Length(max = 20, message = "El c\u00F3digo interno no puede superar los 20 caracteres")
    @Column(name = "CODIGO_INTERNO")
    private String codigoInterno;

    @Length(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PROVEEDOR_ID")
    private Long proveedorId;

    @Column(name = "NOMBRE_FANTASIA")
    private String nombreFantasia;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "SECCION_ID")
    private Seccion seccion;

    @ManyToOne
    @JoinColumn(name = "FAMILIA_ID")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name = "SUBFAMILIA_ID")
    private SubFamilia subfamilia;

    @ManyToOne
    @JoinColumn(name = "MARCA_ID")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "UNIDAD_ID")
    private Unidad unidad;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "GRAMAJE")
    private int gramaje;

    @Column(name = "ESTADO")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private ProductoStock productoStock;

    @NotNull(message = "La lista precio no puede estar vacia")
    @OneToMany(mappedBy = "producto")
    private Set<ProductoListaPrecio> productoListaPrecios;

    @Digits(integer = 12, fraction = 2)
    @Range(min = 0, max = 100, message = "El impuesto iva no puede ser menor a 0 y no puede ser mayor a 100")
    @Column(name = "IMPUESTOIVA")
    private BigDecimal impuestoIva;

    public Producto() {

    }

    public Producto(Long id, @NotNull(message = "El codigo no puede estar vacio") @Length(max = 13, message = "El c\u00F3digo no puede superar los 13 caracteres") String codigo, @Length(max = 20, message = "El c\u00F3digo interno no puede superar los 20 caracteres") String codigoInterno, @Length(max = 255, message = "La descripcion no puede superar los 255 caracteres") String descripcion, Long proveedorId, String nombreFantasia, Departamento departamento, Seccion seccion, Familia familia, SubFamilia subfamilia, Marca marca, Unidad unidad, @Digits(integer = 12, fraction = 2) int gramaje, boolean estado, ProductoStock productoStock, @NotNull(message = "La lista precio no puede estar vacia") Set<ProductoListaPrecio> productoListaPrecios, @Digits(integer = 12, fraction = 2) @Range(min = 0, max = 100, message = "El impuesto iva no puede ser menor a 0 y no puede ser mayor a 100") BigDecimal impuestoIva) {
        this.id = id;
        this.codigo = codigo;
        this.codigoInterno = codigoInterno;
        this.descripcion = descripcion;
        this.proveedorId = proveedorId;
        this.nombreFantasia = nombreFantasia;
        this.departamento = departamento;
        this.seccion = seccion;
        this.familia = familia;
        this.subfamilia = subfamilia;
        this.marca = marca;
        this.unidad = unidad;
        this.gramaje = gramaje;
        this.estado = estado;
        this.productoStock = productoStock;
        this.productoListaPrecios = productoListaPrecios;
        this.impuestoIva = impuestoIva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public int getGramaje() {
        return gramaje;
    }

    public void setGramaje(int gramaje) {
        this.gramaje = gramaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public ProductoStock getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(ProductoStock productoStock) {
        this.productoStock = productoStock;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public SubFamilia getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(SubFamilia subfamilia) {
        this.subfamilia = subfamilia;
    }

    public Set<ProductoListaPrecio> getProductoListaPrecios() {
        return productoListaPrecios;
    }

    public void setProductoListaPrecios(Set<ProductoListaPrecio> productoListaPrecios) {
        this.productoListaPrecios = productoListaPrecios;
    }

    public BigDecimal getImpuestoIva() {
        return impuestoIva;
    }

    public void setImpuestoIva(BigDecimal impuestoIva) {
        this.impuestoIva = impuestoIva;
    }


}
