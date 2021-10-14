package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Departamento;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class DepartamentoRepository implements PanacheRepositoryBase<Departamento, Long> {

    private static final Logger LOGGER = Logger.getLogger(DepartamentoRepository.class.getName());

    public List<Departamento> listar(int pagina, int items) throws PersistanceLayerException {
        try {

                PanacheQuery<Departamento> query = this.findAll();
                query.page(pagina, items);
                return query.list();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    /**
     * Metodo encargado de cambiar el estado de un departamento
     * @param id: identificador del departamento
     * @throws PersistanceLayerException: excepcion en la capa de persistencia
     */
    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            Departamento departamento = this.findById(id);
            this.update("estado = ?1 where id = ?2", !departamento.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_DEPARTAMENTO_EXCEPTION);
        }
    }

}
