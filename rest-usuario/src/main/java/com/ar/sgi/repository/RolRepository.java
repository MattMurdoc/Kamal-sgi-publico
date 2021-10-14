package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Rol;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RolRepository implements PanacheRepositoryBase<Rol, Long> {

    java.util.logging.Logger LOGGER = Logger.getLogger(RolRepository.class.getName());

    public List<Rol> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Rol> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_ROL_EXCEPTION);
        }

    }
}
