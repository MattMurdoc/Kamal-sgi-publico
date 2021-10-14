package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.ProductoListaPrecio;
import com.ar.sgi.model.ProductoListaPrecioId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductoListaPrecioRepository implements PanacheRepositoryBase<ProductoListaPrecio, ProductoListaPrecioId> {
    private static final Logger LOGGER = Logger.getLogger(ProductoListaPrecioRepository.class.getName());

    public List<ProductoListaPrecio> listar() throws PersistanceLayerException {
        try {
            return this.listAll();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PRODUCTO_LISTA_EXCEPTION);
        }
    }
}

