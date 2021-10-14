package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Familia;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.SubFamilia;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SubFamiliaRepository implements PanacheRepositoryBase<SubFamilia, Long> {

    private static final Logger LOGGER = Logger.getLogger(SubFamiliaRepository.class.getName());

    public Paginator<SubFamilia> listar(int pagina, int items, Familia familia, Boolean estado) throws PersistanceLayerException {
        try {
            PanacheQuery<SubFamilia> query;
            Map<String, Object> params = new HashMap<>();
            params.put("familia", familia);
            params.put("estado", estado);
            query = this.find("familia = :familia and estado = :estado", params);
            query.page(pagina, items);
            List<SubFamilia> subFamilias = query.list();
            return new Paginator<>(subFamilias, query.count());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    /**
     * Metodo encargado de cambiar el estado de una subfamilia
     * @param id: identificador de la subfamilia
     * @throws PersistanceLayerException: error en la capa de persistencia
     */
    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            SubFamilia subFamilia = this.findById(id);
            this.update("estado = ?1 where id = ?2", !subFamilia.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_SUBFAMILIA_EXCEPTION);
        }
    }
}
