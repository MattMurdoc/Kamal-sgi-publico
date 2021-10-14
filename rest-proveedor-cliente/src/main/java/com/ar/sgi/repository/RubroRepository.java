package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Rubro;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RubroRepository implements PanacheRepositoryBase<Rubro, Long> {

    Logger LOGGER = Logger.getLogger(RubroRepository.class.getName());

    public List<Rubro> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Rubro> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_RUBRO_EXCEPTION);
        }

    }

}