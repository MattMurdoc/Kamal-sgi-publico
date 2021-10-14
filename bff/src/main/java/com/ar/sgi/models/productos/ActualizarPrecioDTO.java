package com.ar.sgi.models.productos;

import java.util.List;

public class ActualizarPrecioDTO {
    List<Long> ids;
    List<ProductoListaPrecioDTO> productoListaPrecios;

    public ActualizarPrecioDTO() { }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<ProductoListaPrecioDTO> getProductoListaPrecios() {
        return productoListaPrecios;
    }

    public void setProductoListaPrecios(List<ProductoListaPrecioDTO> productoListaPrecios) {
        this.productoListaPrecios = productoListaPrecios;
    }
}
