package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.ProductoListaPrecio;
import com.ar.sgi.repository.ProductoListaPrecioRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductoListaPrecioService {
    private static final Logger LOGGER = Logger.getLogger(ProductoListaPrecioService.class.getName());
    @Inject
    ProductoListaPrecioRepository productoListaPrecioRepository;

    public List<ProductoListaPrecio> listar() throws PersistanceLayerException, ServiceLayerException {
        List<ProductoListaPrecio> productoListaPrecios = null;
        try {
            productoListaPrecios = productoListaPrecioRepository.listar();
            return productoListaPrecios;
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PRODUCTO_LISTA_PRECIO_EXCEPTION);
        }
    }
    @Transactional
    public void agregar(ProductoListaPrecio productoListaPrecio) throws PersistanceLayerException{
        try{
            productoListaPrecioRepository.persist(productoListaPrecio);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.CREAR_PRODUCTO_LISTA_PRECIO_EXCEPTION);
        }
    }
    @Transactional
    public void actualizar(ProductoListaPrecio productoListaPrecio) throws  PersistanceLayerException{
        try{
            productoListaPrecioRepository.getEntityManager().merge(productoListaPrecio);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION);
        }
    }
}
