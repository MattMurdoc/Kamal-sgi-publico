package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Producto;
import com.ar.sgi.model.ProductoStock;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductoStockRepository implements PanacheRepositoryBase<ProductoStock, ProductoStock> {

    private static final Logger LOGGER = Logger.getLogger(ProductoRepository.class.getName());

    public void descontarStock(ProductoStock productoStock, Long cantidad) {
        productoStock.setStock(productoStock.getStock() - cantidad);
        this.getEntityManager().merge(productoStock);
    }

    public void aumentarStock(ProductoStock productoStock, Long cantidad) {
        productoStock.setStock(productoStock.getStock() + cantidad);
        this.getEntityManager().merge(productoStock);
    }
}
