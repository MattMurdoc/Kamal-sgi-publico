package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Permiso;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PermisoRepository implements PanacheRepositoryBase<Permiso, Long> {

    private static final Logger LOGGER = Logger.getLogger(PermisoRepository.class.getName());

    public Paginator<Permiso> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<Permiso> paginator = null;

        try {
            PanacheQuery<Permiso> query = this.findAll();
            query.page(Page.of(pagina, items));
            List<Permiso> permiso = query.list();
            paginator = permiso != null && !permiso.isEmpty() ? new Paginator<Permiso>(permiso, this.count()) : null;
        } catch(Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PERMISO_EXCEPTION);
        }
        return paginator;
    }

    public void eliminarPermiso(Long id) throws PersistanceLayerException {
        try {
            Permiso permiso = this.findById(id);
            this.delete(permiso);
        } catch(Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.BORRAR_PERMISO_EXCEPTION);
        }
    }
}
