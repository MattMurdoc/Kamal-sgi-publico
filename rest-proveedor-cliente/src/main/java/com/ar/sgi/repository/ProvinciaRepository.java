package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.Provincia;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProvinciaRepository implements PanacheRepositoryBase<Provincia, Long> {

    Logger LOGGER = Logger.getLogger(ProvinciaRepository.class.getName());

    public List<Provincia> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Provincia> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PROVINCIA_EXCEPTION);
        }

    }

}