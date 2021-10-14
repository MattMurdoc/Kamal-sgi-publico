package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Familia;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Seccion;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class FamiliaRepository implements PanacheRepositoryBase<Familia, Long> {

    private static final Logger LOGGER = Logger.getLogger(FamiliaRepository.class.getName());

    public Paginator<Familia> listar(int pagina, int items, Seccion seccion, Boolean estado) throws PersistanceLayerException {
        try {
            PanacheQuery<Familia> query;
            Map<String, Object> params = new HashMap<>();
            params.put("seccion", seccion);
            params.put("estado", estado);
            query = this.find("seccion = :seccion and estado = :estado", params);
            query.page(pagina, items);
            List<Familia> familias = query.list();
            return new Paginator<Familia>(familias, query.count());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    /**
     * Metodo encargado de cambiar el estado de una familia
     * @param id: identificador de la familia
     * @throws PersistanceLayerException: error en la capa de persistencia
     */
    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            Familia familia = this.findById(id);
            this.update("estado = ?1 where id = ?2", !familia.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

}
