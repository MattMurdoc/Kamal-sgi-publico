package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Provincia;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class LocalidadRepository implements PanacheRepositoryBase<Localidad, Long> {

    Logger LOGGER = Logger.getLogger(LocalidadRepository.class.getName());

    public List<Localidad> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Localidad> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_LOCALIDAD_EXCEPTION);
        }

    }

    public List<Localidad> listarByProvincia(Provincia provincia )throws PersistanceLayerException {
        try{
            PanacheQuery<Localidad> query = null;
            query = this.find("provincia", provincia);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_LOCALIDAD_EXCEPTION);
        }

    }

}