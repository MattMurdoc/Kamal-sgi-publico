package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Seccion;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SeccionRepository implements PanacheRepositoryBase<Seccion, Long> {

    private static final Logger LOGGER = Logger.getLogger(SeccionRepository.class.getName());

    public List<Seccion> listar(int pagina, int items, Long departamentoId) throws PersistanceLayerException {
        try {
            PanacheQuery<Seccion> query;
            if (departamentoId != null) {
                query = this.find("departamento", departamentoId);
            } else {
                query = this.findAll();
            }
            query.page(pagina, items);

            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    public Paginator<Seccion> listarPaginado(int pagina, int items, Departamento departamento, boolean estado) throws PersistanceLayerException {
        try {
            PanacheQuery<Seccion> query;
            Map<String, Object> params = new HashMap<>();
            params.put("departamento", departamento);
            params.put("estado", estado);
            query = this.find("departamento = :departamento and estado = :estado", params);
            query.page(pagina, items);
            List<Seccion> secciones = query.list();
            return new Paginator<Seccion>(secciones, query.count());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    /**
     * Metodo encargado de cambiar el estado de una seccion
     * @param id: identificador de la seccion
     * @throws PersistanceLayerException: error en la capa de persistencia
     */
    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            Seccion seccion = this.findById(id);
            this.update("estado = ?1 where id = ?2", !seccion.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_SECCION_EXCEPTION);
        }
    }
}
