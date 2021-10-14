package com.ar.sgi.models.productos;

public class ProductoFiltroDTO {

    String codigo, codigoInterno, descripcion, marca, proveedor;
    Long departamento, seccion, familia, subFamilia, unidad;
    int gramaje;

    public ProductoFiltroDTO() {
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getSeccion() {
        return seccion;
    }

    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }

    public Long getFamilia() {
        return familia;
    }

    public void setFamilia(Long familia) {
        this.familia = familia;
    }

    public Long getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(Long subFamilia) {
        this.subFamilia = subFamilia;
    }

    public Long getUnidad() {
        return unidad;
    }

    public void setUnidad(Long unidad) {
        this.unidad = unidad;
    }

    public int getGramaje() {
        return gramaje;
    }

    public void setGramaje(int gramaje) {
        this.gramaje = gramaje;
    }
}
