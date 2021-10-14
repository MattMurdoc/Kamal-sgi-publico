package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.Provincia;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ContactoRepository implements PanacheRepositoryBase<Contacto, Long> {

    Logger LOGGER = Logger.getLogger(ContactoRepository.class.getName());

    public List<Contacto> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Contacto> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_CONTACTO_EXCEPTION);
        }
    }

    public List<Contacto> listarByProveedor(Proveedor proveedor )throws PersistanceLayerException {
        try{
            PanacheQuery<Contacto> query = null;
            query = this.find("proveedor", proveedor);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_CONTACTO_EXCEPTION);
        }
    }
}